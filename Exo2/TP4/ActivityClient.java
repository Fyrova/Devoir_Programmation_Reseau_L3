package TP4;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ActivityClient {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Adresse IP ou nom à tester : ");
        String target = sc.nextLine();
        try (Socket s = new Socket(target, 1027);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            String response = in.readLine();
            System.out.println("Réponse du serveur (" + target + ") : " + response);
        } catch (IOException e) {
            System.out.println("Machine " + target + " inactive ou serveur injoignable.");
        }
        sc.close();
    }
}
