package com.keyvaluestore;

import java.util.HashMap;

public class KeyValueStore {

    private HashMap<String, Node> store;
    private DoublyLinkedList list;
    private int capacity;
    private Storage storage;

    private int setCount;
    private int getCount;
    private int deleteCount;

    public KeyValueStore(int capacity) {
        this.capacity = capacity;
        store = new HashMap<>();
        list = new DoublyLinkedList();
        storage = new Storage();

        setCount = 0;
        getCount = 0;
        deleteCount = 0;
    }

    public synchronized void set(String key, String value, int ttlSeconds) {

        Node existingNode = store.get(key);

        if (existingNode != null) {

            existingNode.value = value;

            if (ttlSeconds == 0) {
                existingNode.expiryTime = 0;
            } else {
                existingNode.expiryTime =
                        System.currentTimeMillis() + (ttlSeconds * 1000L);
            }

            list.moveToLast(existingNode);

        } else {

            Node node = new Node(key, value);

            if (ttlSeconds == 0) {
                node.expiryTime = 0;
            } else {
                node.expiryTime =
                        System.currentTimeMillis() + (ttlSeconds * 1000L);
            }

            if (store.size() >= capacity) {

                Node leastRecentlyUsed = list.head;

                list.removeNode(leastRecentlyUsed);
                store.remove(leastRecentlyUsed.key);
            }

            store.put(key, node);
            list.addLast(node);
        }

        setCount++;

        storage.saveAll(store);
    }

    public synchronized String get(String key) {

        getCount++;

        Node node = store.get(key);

        if (node != null) {

            if (node.expiryTime > 0 &&
                    System.currentTimeMillis() > node.expiryTime) {

                list.removeNode(node);
                store.remove(key);

                storage.saveAll(store);

                return null;
            }

            list.moveToLast(node);

            return node.value;
        }

        return null;
    }

    public synchronized void delete(String key) {

        deleteCount++;

        Node node = store.get(key);

        if (node != null) {

            list.removeNode(node);
            store.remove(key);

            storage.saveAll(store);
        }
    }

    public synchronized void loadData(String key, String value) {

        Node existingNode = store.get(key);

        if (existingNode != null) {

            existingNode.value = value;
            existingNode.expiryTime = 0;

            list.moveToLast(existingNode);

        } else {

            Node node = new Node(key, value);

            node.expiryTime = 0;

            if (store.size() >= capacity) {

                Node leastRecentlyUsed = list.head;

                list.removeNode(leastRecentlyUsed);
                store.remove(leastRecentlyUsed.key);
            }

            store.put(key, node);
            list.addLast(node);
        }
    }

    public synchronized String getStats() {

        return "SET: " + setCount +
                "\nGET: " + getCount +
                "\nDELETE: " + deleteCount;
    }
}