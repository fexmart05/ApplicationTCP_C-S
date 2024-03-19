/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author federico
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server avviato sulla porta " + port);
            clientSocket = serverSocket.accept();
            System.out.println("Connessione accettata: " + clientSocket);

            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Errore durante l'avvio del server: " + e.getMessage());
            close();
        }
    }

    public void sendMessage(String message) {
        outputWriter.println(message);
    }

    public String receiveMessage() throws IOException {
        return inputReader.readLine();
    }

    public void close() {
        try {
            if (inputReader != null)
                inputReader.close();
            if (outputWriter != null)
                outputWriter.close();
            if (clientSocket != null)
                clientSocket.close();
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura del server: " + e.getMessage());
        }
    }
}
