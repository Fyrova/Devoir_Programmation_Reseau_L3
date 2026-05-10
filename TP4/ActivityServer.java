package TP4;
import java.io.*;
import java.net.*;
import java.time.LocalTime;
public class ActivityServer {
     public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("Serveur d'activité sur port 1027");
        while (true) {
            Socket client = server.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println(LocalTime.now().toString() + " - Machine active");
            client.close();
        }
    }
    
}
