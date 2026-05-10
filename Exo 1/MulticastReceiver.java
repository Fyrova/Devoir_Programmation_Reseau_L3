import java.net.*; 
import java.io.*;

public class MulticastReceiver {
public static void main(String[] args) {
try {
boolean done = false;

byte[] buffer = new byte[1024];
DatagramPacket dp = new
DatagramPacket(buffer, buffer.length);
InetAddress ia =
InetAddress.getByName(args[0]);
int port = Integer.parseInt(args[1]);
MulticastSocket ms = new
MulticastSocket(port);

ms.setReuseAddress(true); 
ms.joinGroup(ia);
System.out.println("joined multicast group"+ ia);
while (!done) {
ms.receive(dp);
System.out.println("Received " + dp.getLength()
+ " bytes from " + dp.getAddress() + ": " + new
String(dp.getData(),0,dp.getLength()));}
ms.leaveGroup(ia);
ms.close();}
catch (Exception e) { System.err.println(e);}}}