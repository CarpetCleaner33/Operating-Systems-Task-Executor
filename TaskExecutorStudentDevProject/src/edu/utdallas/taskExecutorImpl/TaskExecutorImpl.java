package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.BlockingQueue.BlockingQueue; // Imports blocking queue implementation.


public class TaskExecutorImpl implements TaskExecutor
{
    private final Thread[] threads; // Final keyword is used to make sure that the threads array is not re-assigned.
    
    public TaskExecutorImpl(int threadPoolSize)
    {
        //TODO Complete the implementation
		// Initialize the thread pool
        threads = new Thread[threadPoolSize]; // Create a new object array of threads with the size of the thread pool.
        
        for (int i = 0; i < threadPoolSize; i++) // Create a new instance of thread for each thread in the thread pool.
        {
            threads[i] = new Thread(new TaskRunner()); // New instance of TaskRunner is created and passed to the thread.
            threads[i].setName("TaskThread" + i); // Set the name of the thread.
            threads[i].start(); // Start the thread.
        }
    }

    @Override
    public void addTask(Task task)
    {
        // TODO Complete the implementation
		// Will be implemented once the blocking queue is ready
    }

    private class TaskRunner implements Runnable
    {
        @Override
        public void run()
        {
            while (true)
            {
                // Retrieve and execute tasks from the blocking queue (to be implemented)
                Task task = null; // This will be retrieved from the queue later
                
                try
                {
                    if (task != null)
                    {
                        task.execute();
                    }
                }
                catch (Exception e)
                {
                    // Handle exception and ensure the thread continues running
                    System.err.println("Task execution error: " + e.getMessage());
                }
            }
        }
    }
}
