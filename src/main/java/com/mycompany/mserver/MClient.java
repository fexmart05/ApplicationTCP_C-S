/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author federico
 */
public class MClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost"; // Indirizzo IP del server
        final int SERVER_PORT = 12345; // Porta del server

        Client client = new Client(SERVER_ADDRESS, SERVER_PORT);

        // Avvio del thread per la ricezione dei messaggi dal server
        new Thread(() -> {
            try {
                String message;
                while ((message = client.receiveMessage()) != null) {
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                System.err.println("Errore durante la ricezione dei messaggi dal server: " + e.getMessage());
                client.close();
            }
        }).start();

        // Lettura dei messaggi da tastiera e invio al server
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
            String message;
            while ((message = inputReader.readLine()) != null) {
                client.sendMessage(message);
                if (message.equalsIgnoreCase("exit")) {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dei messaggi da tastiera: " + e.getMessage());
            client.close();
        }
    }
}