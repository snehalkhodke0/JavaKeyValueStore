package com.keyvaluestore;

public class Node {

    String key;
    String value;

    long expiryTime;

    Node previous;
    Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.expiryTime = 0;
    }
}