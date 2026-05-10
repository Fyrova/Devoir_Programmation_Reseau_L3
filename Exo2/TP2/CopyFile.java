package Exo2.TP2;
import java.io.*;
import java.util.Scanner;

public class CopyFile {
      public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Fichier source : ");
        String in = sc.nextLine();
        System.out.print("Fichier destination : ");
        String out = sc.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(in));
             PrintStream ps = new PrintStream(new FileOutputStream(out))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ps.println(line);
            }
        }
        sc.close();
    }
    
}
