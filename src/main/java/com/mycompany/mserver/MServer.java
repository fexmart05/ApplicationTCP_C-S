/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mserver;

import java.util.Scanner;
/**
 *
 * @author federico
 */


public class MServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int port = 12345;

        do {
            try {
                System.out.print("Inserisci un numero di porta valido in cui avviare il Server (0 - 65535): ");
                port = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Errore: hai inserito un valore non valido.");
            }
        } while (port < 0 || port > 65535);

        Server server = new Server(port);
    }
}
