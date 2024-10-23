package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.BlockingQueue.BlockingQueue; // Import the BlockingQueue class.

public class TaskExecutorImpl implements TaskExecutor // Implement the TaskExecutor interface.
{
	// final as the threads and taskQueue are initialized in the constructor and will not be reassigned.
    private final Thread[] threads; // Initialize an array of threads.
    private final BlockingQueue<Task> taskQueue; // Initialize a BlockingQueue of tasks.

    public TaskExecutorImpl(int threadPoolSize) // Constructor for the TaskExecutorImpl class.
    {
        threads = new Thread[threadPoolSize]; // Initialize the array of threads with the specified size.
        taskQueue = new BlockingQueue<>(100); // Initialize the BlockingQueue with a capacity of 100.

        for (int i = 0; i < threadPoolSize; i++)
        {
            threads[i] = new Thread(new TaskRunner()); // Create a new thread with a TaskRunner.
            threads[i].setName("TaskThread" + i); // Set the name of the thread.
            threads[i].start(); // Start the thread.
        }
    }

    @Override // Override the addTask method from the TaskExecutor interface.
    public void addTask(Task task) // Add a task to the task queue.
    {
        try
        {
            taskQueue.put(task); // Add the task to the blocking queue.
        }
        catch (InterruptedException e) // Catch InterruptedException if thrown.
        {
            Thread.currentThread().interrupt(); // Restore interrupted status.
        }
    }

    private class TaskRunner implements Runnable // Implement the Runnable interface for the TaskRunner class.
    {
        @Override // Override the run method from the Runnable interface.
        public void run()
        {
            while (true) // Infinite loop to keep the thread running.
            {
                try
                {
                    Task task = taskQueue.take(); // Take a task from the blocking queue.
                    task.execute(); // Task from the queue is executed.
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt(); // Restore interrupted status.
                    break; // Exit the loop if interrupted.
                }
            }
        }
    }
}
