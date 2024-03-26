/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mserver;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author federico
 */

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final int port;
    private BufferedWriter output;
    private BufferedReader input;
    private final Scanner scanner;

    public Server(int port) {
        this.port = port;
        this.scanner = new Scanner(System.in);
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server avviato e in ascolto sulla porta " + port + "!");
            this.clientSocket = waitForConnection();

            if (clientSocket != null) {
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String connectionMessage = input.readLine();
                System.out.println(connectionMessage);

                readMessages();
                writeMessages();
            } else {
                System.err.println("Impossibile creare la socket con il Client!");
                close();
            }

        } catch (BindException e) {
            System.err.println("Errore: non è possibile mettere il Server in ascolto sulla porta " + port);
            close();
        } catch (IOException e) {
            System.err.println("Errore nell'apertura del Server: " + e.getMessage());
            close();
        }
    }

    public Socket waitForConnection() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Errore nell'attesa di connessioni: " + e.getMessage());
            return null;
        }
    }

    public void writeMessages() {
        System.out.println("Server pronto all'invio di messaggi! Digita 'exit' per terminare.");
        while (!clientSocket.isClosed() && !serverSocket.isClosed()) {
            try {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    output.write("exit");
                    output.newLine();
                    output.flush();
                    System.out.println("Chiusura chat in corso...");
                    close();
                } else {
                    output.write("SERVER: " + message);
                    output.newLine();
                    output.flush();
                }
            } catch (IOException e) {
                System.err.println("Errore in scrittura: " + e.getMessage());
                close();
            }
        }
    }

    public void readMessages() {
        new Thread(() -> {
            try {
                while (!clientSocket.isClosed() && !serverSocket.isClosed()) {
                    String message = input.readLine();
                    if (message == null || message.equalsIgnoreCase("exit")) {
                        System.out.println("Il Client ha lasciato la conversazione.");
                        close();
                        break;
                    } else {
                        System.out.println(message);
                    }
                }
            } catch (IOException e) {
                if (!e.getMessage().equals("Socket closed")) {
                    System.err.println("Si è verificato un errore: " + e.getMessage());
                }
                close();
            }
        }).start();
    }

    public void close() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            System.err.println("Errore in chiusura34114: " + e.getMessage());
        }
        System.exit(0);
    }
}
