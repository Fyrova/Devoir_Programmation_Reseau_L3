package TP3;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddr = InetAddress.getByName("localhost");
        int port = 9876;
        Scanner sc = new Scanner(System.in);
        System.out.println("Client UDP. Tapez vos messages (Ctrl+C pour quitter) :");
        while (true) {
            String line = sc.nextLine();
            byte[] data = line.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddr, port);
            socket.send(packet);
        }
    }
    
}
