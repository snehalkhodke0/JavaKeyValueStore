# Thread-Safe In-Memory Key-Value Store with LRU Cache

A Core Java project that implements an in-memory key-value store with LRU cache, multithreading, TCP client-server communication, TTL, file persistence, and statistics.

## Features

- SET, GET and DELETE operations
- HashMap for fast key lookup
- Doubly Linked List for LRU cache
- Least Recently Used (LRU) eviction
- TCP client-server communication
- Multiple client support
- Multithreading
- Thread-safe operations using synchronization
- TTL-based key expiration
- File persistence using File I/O
- SET, GET and DELETE statistics

## Technologies Used

- Core Java
- OOP
- HashMap
- Doubly Linked List
- Multithreading
- TCP Socket Programming
- File I/O

## How to Run

1. Open the project in IntelliJ IDEA.
2. Run `KeyValueServer.java`.
3. Run `KeyValueClient.java`.
4. Use commands such as:

```text
SET name Snehal 0
GET name
DELETE name
STATS
QUIT

PROJECT STRUCTURE
src/com/keyvaluestore/
├── KeyValueServer.java
├── KeyValueClient.java
├── KeyValueStore.java
├── DoublyLinkedList.java
├── Node.java
├── Storage.java
└── Main.java
