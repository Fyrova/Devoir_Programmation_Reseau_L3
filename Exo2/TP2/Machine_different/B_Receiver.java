package Exo2.TP2.Machine_different;
import java.io.*;
import java.net.*;

public class B_Receiver {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("B en attente de connexion sur le port 1027...");
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            if (line.equals("stop")) break;
            System.out.println("Reçu : " + line);
        }
        socket.close();
        server.close();
    }
    
}
