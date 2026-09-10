package com.keyvaluestore;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class KeyValueServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for clients...");

            KeyValueStore store = new KeyValueStore(3);

            Storage storage = new Storage();
            storage.load(store);

            System.out.println("Saved data loaded!");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Client connected!");

                Thread thread = new Thread(() -> {

                    try {
                        BufferedReader reader =
                                new BufferedReader(
                                        new InputStreamReader(
                                                socket.getInputStream()));

                        PrintWriter writer =
                                new PrintWriter(
                                        socket.getOutputStream(), true);

                        String message;

                        while ((message = reader.readLine()) != null) {

                            System.out.println("Client says: " + message);

                            String[] parts = message.split(" ");

                            if (parts[0].equals("SET")) {

                                String key = parts[1];
                                String value = parts[2];
                                int ttl = Integer.parseInt(parts[3]);

                                store.set(key, value, ttl);

                                writer.println("OK");
                            }

                            else if (parts[0].equals("GET")) {

                                String value = store.get(parts[1]);

                                writer.println(value);
                            }

                            else if (parts[0].equals("DELETE")) {

                                store.delete(parts[1]);

                                writer.println("OK");
                            }

                            else if (parts[0].equals("PING")) {

                                writer.println("PONG");
                            }

                            else if (parts[0].equals("STATS")) {

                                writer.println(store.getStats());
                            }

                            else if (parts[0].equals("QUIT")) {

                                writer.println("BYE");
                                break;
                            }
                        }

                        socket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}