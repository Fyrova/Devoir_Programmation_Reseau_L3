package Exo2.TP2.Exercice2_e;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class A_SenderStopByB {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1027);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner sc = new Scanner(System.in);
        
        
        Thread listenThread = new Thread(() -> {
            try {
                String msg = in.readLine();
                if (msg != null && msg.equals("stop")) {
                    System.out.println("B a demandé l'arrêt. Fin du programme.");
                    System.exit(0);
                }
            } catch (IOException e) { e.printStackTrace(); }
        });
        listenThread.start();
        
        String line;
        while (!(line = sc.nextLine()).equals("stop")) {
            out.println(line);
        }
        socket.close();
        sc.close();
    }
    
}
