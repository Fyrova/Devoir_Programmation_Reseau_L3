package Exo2.TP2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class A_Sender  {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1027);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("stop")) {
            out.println(line);
        }
        out.println("stop");
        socket.close();
        sc.close();
    }
    
}
