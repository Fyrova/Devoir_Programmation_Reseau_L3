import java.net.*;
import java.io.*;
public class MulticastSender {
public static void main(String[] args) {
BufferedReader stdin; //input clavier
String sendString; //chaîne à envoyer
byte[] sendBytes; //octets à envoyer1
try {
InetAddress ia =
InetAddress.getByName(args[0]);
int port = Integer.parseInt(args[1]);
MulticastSocket ms = new
MulticastSocket();
ms.joinGroup(ia);
stdin = new BufferedReader(new
InputStreamReader(System.in));
System.out.println("Begin typing (return to send,"
+ " ctrl-C to quit):");
while ((sendString = stdin.readLine()) != null) {

sendBytes = sendString.getBytes();

DatagramPacket packet = new
DatagramPacket(sendBytes,sendBytes.length, ia,port );
//envoyer packet
ms.send(packet);}
ms.close();}
catch (Exception e) { System.err.println(e); } }
}