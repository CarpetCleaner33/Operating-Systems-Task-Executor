package edu.utdallas.BlockingQueue;

import java.util.LinkedList; // Import the LinkedList class.
import java.util.Queue; // Import the Queue class.

public class BlockingQueue<T> // BlockingQueue class with generic type T.
{
    private final Queue<T> queue = new LinkedList<T>(); // Create a new queue object with generic type T.
    private final int queue_capacity; // The capacity of the queue.

    public BlockingQueue(int capacity) // Constructor for the BlockingQueue class.
    {
        this.queue_capacity = capacity; // Set the capacity of the queue.
    }

    public void put(T item) throws InterruptedException // Add an item to the queue.
    {
        synchronized(this) // Synchronize the block of code to ensure thread safety, avoids race conditions.
        {
            while(queue.size() == queue_capacity) // If the queue is full, wait until there is space.
            {
                wait(); // Wait until there is space in the queue.
            }
            queue.add(item); // Add the item to the queue.
            notifyAll(); // Notify all threads that are waiting.
        }
    }

    public T take() throws InterruptedException // Remove and return an item from the queue.
    {
        synchronized(this) // Synchronize the block of code to ensure thread safety.
        {
            while(queue.isEmpty()) // If the queue is empty, wait until there is an item.
            {
                wait(); // Wait until there is an item in the queue.
            }
            T item = queue.remove(); // Remove and return the item from the queue.
            notifyAll(); // Notify all threads that are waiting.
            return item; // Return the item.
        }
    }
}
