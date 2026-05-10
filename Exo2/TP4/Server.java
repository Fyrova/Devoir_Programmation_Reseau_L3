package TP4;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("Serveur push sur port 1027");
        while (true) {
            Socket client = server.accept();
            new Thread(new ClientPush(client)).start();
        }
    }
    
    static class ClientPush implements Runnable {
        private Socket socket;
        ClientPush(Socket s) { this.socket = s; }
        public void run() {
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                // Envoyer un message toutes les 5 secondes
                while (!socket.isClosed()) {
                    out.println("Message périodique : " + System.currentTimeMillis());
                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                System.out.println("Client déconnecté");
            }
        }
    }
    
}
