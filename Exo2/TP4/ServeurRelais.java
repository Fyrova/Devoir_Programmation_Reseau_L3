package TP3;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServeurRelais {
    private static final int PORT = 1026;
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Serveur relais sur port " + PORT);
        while (true) {
            Socket client = server.accept();
            new Thread(() -> handleClient(client)).start();
        }
    }
    
    private static void handleClient(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            String request = in.readLine();
            System.out.println("Requête : " + request);
            // Exemple simple : on teste une seule adresse
            String result = testRemoteMachine(request);
            out.println(result);
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    private static String testRemoteMachine(String host) {
        try (Socket s = new Socket(host, 1027);
             BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            return r.readLine();
        } catch (IOException e) {
            return "Machine inactive";
        }
    }
    
}
