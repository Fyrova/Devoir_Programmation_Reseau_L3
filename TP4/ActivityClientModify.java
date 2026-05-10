package TP4;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class ActivityClientModify {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Adresse de base (ex: 192.168.1.) : ");
        String prefix = sc.nextLine();
        System.out.println("Test des 25 machines à partir de " + prefix + "1");
        for (int i = 1; i <= 25; i++) {
            String target = prefix + i;
            try (Socket s = new Socket(target, 1027);
                 BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                String resp = in.readLine();
                System.out.println(target + " : " + resp);
            } catch (IOException e) {
                System.out.println(target + " : inactive");
            }
        }
        sc.close();
    }
    
}
