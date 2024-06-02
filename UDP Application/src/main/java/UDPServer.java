/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dansp
 */
import java.io.*;
import java.net.*;  

public class UDPServer {
    public static void main(String args[]) throws Exception{
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            
            while (true) {                
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                
                System.out.println("Waiting for datagram packet");
                
                serverSocket.receive(receivePacket);
                
                // Mengambil panjang data yang sebenarnya
                int length = receivePacket.getLength();
                String sentence = new String(receivePacket.getData(), 0, length);
                
                InetAddress IPAddress = receivePacket.getAddress();
                
                int port = receivePacket.getPort();
                
                System.out.println("From: " + IPAddress + ":" + port);
                System.err.println("Message: " + sentence);
                
                String capitalizedSentence = sentence.toUpperCase();
                
                sendData = capitalizedSentence.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                
                serverSocket.send(sendPacket);
            }
        } catch (SocketException ex) {
            System.out.println("UDP Port 9876 is occured");
            System.exit(1);
        }
    }
}

