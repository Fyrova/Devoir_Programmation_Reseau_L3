package Exo2.TP2.Machine_different;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class A_Sender {
        public static void main(String[] args) throws IOException {
        
        String serverAddress = "192.168.1.10";
        Socket socket = new Socket(serverAddress, 1027);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Saisissez du texte (\"stop\" pour terminer) :");
        while (!(line = sc.nextLine()).equals("stop")) {
            out.println(line);
        }
        out.println("stop");
        socket.close();
        sc.close();
    }
}
