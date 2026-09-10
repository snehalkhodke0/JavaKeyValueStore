package com.keyvaluestore;

import java.io.*;
import java.net.Socket;

public class KeyValueClient {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);

            PrintWriter writer =
                    new PrintWriter(socket.getOutputStream(), true);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            writer.println("GET city");
            System.out.println("GET after restart: " + reader.readLine());

            writer.println("QUIT");
            System.out.println("QUIT: " + reader.readLine());

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}