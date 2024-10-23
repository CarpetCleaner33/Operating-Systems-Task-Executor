package edu.utdallas.BlockingQueue;

import java.util.LinkedList; // Import the LinkedList class.
import java.util.Queue; // Import the Queue class.

public class BlockingQueue
{
    private final Queue<T> queue = new LinkedList<T>(); // Create a new queue object.
    private final int queue_capacity; // The capacity of the queue.

    public BlockingQueue(int capacity) // Constructor for the BlockingQueue class.
    {
        this.queue_capacity = capacity; // Set the capacity of the queue.
    }
}
