package Exo2.TP2.Exercice2_e;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class B_ReceiverWithStop {
     public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("B en attente...");
        Socket socket = server.accept();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
       
        Thread keyboardThread = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.println("Tapez 'stop' pour terminer");
            while (true) {
                String cmd = sc.nextLine();
                if (cmd.equals("stop")) {
                    out.println("stop");
                    break;
                }
            }
            sc.close();
        });
        keyboardThread.start();
        
        
        String line;
        while ((line = in.readLine()) != null) {
            if (line.equals("stop")) break;
            System.out.println("Reçu de A : " + line);
        }
        socket.close();
        server.close();
    }
    
}
