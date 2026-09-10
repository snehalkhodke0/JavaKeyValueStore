package com.keyvaluestore;

import java.io.*;

public class Storage {

    public void saveAll(java.util.HashMap<String, Node> store) {

        try {
            FileWriter writer =
                    new FileWriter("data.txt");

            for (Node node : store.values()) {
                writer.write(node.key + "=" + node.value + "\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(KeyValueStore store) {

        try {
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("data.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("=", 2);

                if (parts.length < 2) {
                    continue;
                }

                String key = parts[0];
                String value = parts[1];

                store.loadData(key, value);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No saved data found.");
        }
    }
}