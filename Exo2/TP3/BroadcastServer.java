package TP3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BroadcastServer {
    private static List<PrintWriter> clients = new CopyOnWriteArrayList<>();
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("Serveur démarré sur port 1027");
        while (true) {
            Socket client = server.accept();
            System.out.println("Nouveau client : " + client.getRemoteSocketAddress());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            clients.add(out);
            new Thread(new ClientHandler(client, out)).start();
        }
    }
    
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        ClientHandler(Socket s, PrintWriter out) { this.socket = s; this.out = out; }
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Reçu : " + msg);
                    // Diffuser à tous
                    for (PrintWriter writer : clients) {
                        writer.println(msg);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client déconnecté");
            } finally {
                clients.remove(out);
                try { socket.close(); } catch (IOException e) {}
            }
        }
    }
    
}
