/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;
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
public class Client_UDP {
    public static void main(String[] args) throws SocketException, IOException {
        Scanner sc = new Scanner (System.in);
        InetAddress IP = InetAddress.getByName("localhost");
        DatagramSocket clientSocket = new DatagramSocket();
        while(true) {
            byte[] sendbuffer = new byte[1024];
            byte[] receivebuffer = new byte[1024];
            System.out.print("\nClient : ");
            String clientData = sc.nextLine();
            sendbuffer = clientData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket (sendbuffer,sendbuffer.length, IP, 9876);
            clientSocket.send(sendPacket);
            if (clientData.equalsIgnoreCase("byte")) {
                System.out.print("Connection ended by client");
                break;
            }
            
            DatagramPacket receivePacket = new DatagramPacket(receivebuffer,receivebuffer.length);
            clientSocket.send(sendPacket);
            String serverData = new String(receivePacket.getData());
            System.out.print("\nServer :" + serverData);
        }
        clientSocket.close();
    }
}
