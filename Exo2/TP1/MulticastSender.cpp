#include <iostream>
#include <string>
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

        // Option pour réutiliser l'adresse (facultatif, bonne pratique)
        int reuse = 1;
        setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &reuse, sizeof(reuse));

        // (Optionnel) Rejoindre le groupe – n'est pas vraiment nécessaire pour l'envoi,
        // mais le code Java le fait, on reproduit.
        struct ip_mreq mreq;
        mreq.imr_multiaddr.s_addr = inet_addr(multicast_ip);
        mreq.imr_interface.s_addr = htonl(INADDR_ANY);
        if (setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, &mreq, sizeof(mreq)) < 0) {
            perror("setsockopt IP_ADD_MEMBERSHIP (warning, continue)");
            // On continue car l'envoi fonctionne souvent sans
        }

        // Adresse de destination
        struct sockaddr_in dest_addr;
        memset(&dest_addr, 0, sizeof(dest_addr));
        dest_addr.sin_family = AF_INET;
        dest_addr.sin_addr.s_addr = inet_addr(multicast_ip);
        dest_addr.sin_port = htons(port);

        std::cout << "Commencez à taper (Entrée pour envoyer, Ctrl+D pour quitter) :"
                  << std::endl;

        std::string line;
        while (std::getline(std::cin, line)) {
            ssize_t sent = sendto(sock, line.c_str(), line.size(), 0,
                                  (struct sockaddr*)&dest_addr, sizeof(dest_addr));
            if (sent < 0) {
                perror("sendto");
                break;
            }
        }

        // Quitter le groupe si on l'avait rejoint
        setsockopt(sock, IPPROTO_IP, IP_DROP_MEMBERSHIP, &mreq, sizeof(mreq));
        close(sock);
    }
    catch (const std::exception& e) {
        std::cerr << "Erreur: " << e.what() << std::endl;
        return 1;
    }

    return 0;
}