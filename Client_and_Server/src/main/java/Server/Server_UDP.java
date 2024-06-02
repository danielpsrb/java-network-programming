/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;
import java.io.IOException;
import java.util.Scanner;
import java.net.SocketException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author dansp
 */
public class Server_UDP {
    public static void main(String[] args) throws SocketException, IOException {
        Scanner sc = new Scanner (System.in);
        DatagramSocket serverSocket = new DatagramSocket (9876);
        
        while(true) {
            byte[] sendbuffer = new byte[1024];
            byte[] receivebuffer = new byte[1024];
            DatagramPacket receivePkt = new DatagramPacket(receivebuffer, receivebuffer.length);
            serverSocket.receive(receivePkt);
            InetAddress IP = receivePkt.getAddress();
            int portno = receivePkt.getPort();
            String clientdata = new String(receivePkt.getData());
            System.out.println("\nClient :" + clientdata);
            System.out.println("\nServer :");
            String serverData = sc.nextLine();
            sendbuffer = serverData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket (sendbuffer, sendbuffer.length, IP, portno);
            serverSocket.send(sendPacket);
            if (serverData.equalsIgnoreCase("byte")) {
                System.out.println("Connection ended by Server");
                break;
            }
        }
    }
}
