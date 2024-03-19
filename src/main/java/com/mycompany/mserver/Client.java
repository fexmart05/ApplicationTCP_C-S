/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author federico
 */
public class Client {
    private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    public Client(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Errore durante la connessione al server: " + e.getMessage());
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
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura del client: " + e.getMessage());
        }
    }
}
