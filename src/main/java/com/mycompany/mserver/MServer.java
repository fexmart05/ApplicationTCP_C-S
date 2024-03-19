/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author federico
 */
public class MServer {
    public static void main(String[] args) {
        final int SERVER_PORT = 12345; // Porta del server

        Server server = new Server(SERVER_PORT);

        // Avvio del thread per la ricezione dei messaggi dal client
        new Thread(() -> {
            try {
                String message;
                while ((message = server.receiveMessage()) != null) {
                    System.out.println("Client: " + message);
                }
            } catch (IOException e) {
                System.err.println("Errore durante la ricezione dei messaggi dal client: " + e.getMessage());
                server.close();
            }
        }).start();

        // Lettura dei messaggi da tastiera e invio al client
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
            String message;
            while ((message = inputReader.readLine()) != null) {
                server.sendMessage(message);
                if (message.equalsIgnoreCase("exit")) {
                    server.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dei messaggi da tastiera: " + e.getMessage());
            server.close();
        }
    }
}