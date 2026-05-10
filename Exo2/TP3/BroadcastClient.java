package TP3;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BroadcastClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1027);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Message reçu : " + msg);
                }
            } catch (IOException e) { e.printStackTrace(); }
        }).start();
        
        Scanner sc = new Scanner(System.in);
        String line;
        while (true) {
            line = sc.nextLine();
            out.println(line);
        }
    }
    
}
