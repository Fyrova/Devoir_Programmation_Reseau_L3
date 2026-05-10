package TP4;
import java.net.*;
import java.util.Scanner;

public class Internet_Address {
     public static void main(String[] args) throws UnknownHostException {
        InetAddress local = InetAddress.getLocalHost();
        System.out.println("Adresse de cette machine : " + local.getHostAddress());
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Entrez un nom d'hôte (\"stop\" pour finir) :");
        while (!(input = sc.nextLine()).equals("stop")) {
            try {
                InetAddress[] addrs = InetAddress.getAllByName(input);
                for (InetAddress addr : addrs) {
                    System.out.println(addr.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("Nom inconnu");
            }
        }
        sc.close();
    }
    
}
