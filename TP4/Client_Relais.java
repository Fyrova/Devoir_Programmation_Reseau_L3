package TP4;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client_Relais {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Adresse distante à interroger : ");
        String remote = sc.nextLine();
        try (Socket s = new Socket("localhost", 1026);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            out.println(remote);
            String answer = in.readLine();
            System.out.println("Résultat : " + answer);
        }
        sc.close();
    }
    
}
