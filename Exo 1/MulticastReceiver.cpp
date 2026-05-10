#include <iostream>
#include <cstring>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <unistd.h>

int main(int argc, char* argv[]) {
    if (argc != 3) {
        std::cerr << "Usage: " << argv[0] << " <multicast_ip> <port>" << std::endl;
        return 1;
    }

    try {
        // Paramètres
        const char* multicast_ip = argv[1];
        int port = std::stoi(argv[2]);

        // Création du socket
        int sock = socket(AF_INET, SOCK_DGRAM, 0);
        if (sock < 0) {
            perror("socket");
            return 1;
        }

        // Option pour réutiliser l'adresse
        int reuse = 1;
        if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &reuse, sizeof(reuse)) < 0) {
            perror("setsockopt SO_REUSEADDR");
            close(sock);
            return 1;
        }

        // Liaison au port spécifié
        struct sockaddr_in local_addr;
        memset(&local_addr, 0, sizeof(local_addr));
        local_addr.sin_family = AF_INET;
        local_addr.sin_addr.s_addr = htonl(INADDR_ANY);
        local_addr.sin_port = htons(port);

        if (bind(sock, (struct sockaddr*)&local_addr, sizeof(local_addr)) < 0) {
            perror("bind");
            close(sock);
            return 1;
        }

        // Adresse multicast
        struct ip_mreq mreq;
        mreq.imr_multiaddr.s_addr = inet_addr(multicast_ip);
        mreq.imr_interface.s_addr = htonl(INADDR_ANY);

        // Rejoindre le groupe multicast
        if (setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, &mreq, sizeof(mreq)) < 0) {
            perror("setsockopt IP_ADD_MEMBERSHIP");
            close(sock);
            return 1;
        }

        std::cout << "Rejoint le groupe multicast " << multicast_ip << std::endl;

        char buffer[1024];
        struct sockaddr_in sender_addr;
        socklen_t sender_len = sizeof(sender_addr);

        while (true) {
            ssize_t len = recvfrom(sock, buffer, sizeof(buffer) - 1, 0,
                                   (struct sockaddr*)&sender_addr, &sender_len);
            if (len < 0) {
                perror("recvfrom");
                break;
            }
            buffer[len] = '\0';
            std::cout << "Reçu " << len << " octets depuis "
                      << inet_ntoa(sender_addr.sin_addr) << ": " << buffer << std::endl;
        }

        // Quitter le groupe (optionnel ici)
        setsockopt(sock, IPPROTO_IP, IP_DROP_MEMBERSHIP, &mreq, sizeof(mreq));
        close(sock);
    }
    catch (const std::exception& e) {
        std::cerr << "Erreur: " << e.what() << std::endl;
        return 1;
    }

    return 0;
}