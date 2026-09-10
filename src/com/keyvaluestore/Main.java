package com.keyvaluestore;

public class Main {

    public static void main(String[] args) {

        KeyValueStore store = new KeyValueStore(3);

        store.set("name", "Snehal", 5);

        System.out.println("Name: " + store.get("name"));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Name after TTL: " + store.get("name"));
    }
}