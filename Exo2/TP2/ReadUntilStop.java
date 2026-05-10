package Exo2.TP2;
import java.util.Scanner;

public class ReadUntilStop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Saisissez du texte (\"stop\" pour quitter) :");
        while (!(line = sc.nextLine()).equals("stop")) {
            System.out.println("Vous avez saisi : " + line);
        }
        sc.close();
    }
    
}
