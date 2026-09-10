package com.keyvaluestore;

public class DoublyLinkedList {

    Node head;
    Node tail;

    public void addFirst(Node node) {

        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
    }
    public void addLast(Node node) {

        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }
    public void removeNode(Node node) {

        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }
    }
    public void moveToLast(Node node) {

        removeNode(node);
        addLast(node);
    }

}