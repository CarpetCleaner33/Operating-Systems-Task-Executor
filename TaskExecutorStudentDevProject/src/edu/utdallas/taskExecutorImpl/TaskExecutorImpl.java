package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.BlockingQueue.BlockingQueue;

public class TaskExecutorImpl implements TaskExecutor
{
    private final Thread[] threads;
    private final BlockingQueue<Task> taskQueue;

    public TaskExecutorImpl(int threadPoolSize)
    {
        threads = new Thread[threadPoolSize];
        taskQueue = new BlockingQueue<>(100); // Example capacity

        for (int i = 0; i < threadPoolSize; i++)
        {
            threads[i] = new Thread(new TaskRunner());
            threads[i].setName("TaskThread" + i);
            threads[i].start();
        }
    }

    @Override
    public void addTask(Task task)
    {
        try
        {
            taskQueue.put(task); // Add the task to the blocking queue
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    private class TaskRunner implements Runnable
    {
        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    Task task = taskQueue.take(); // Take a task from the blocking queue
                    task.execute(); // Execute the task
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    break; // Exit the loop if interrupted
                }
            }
        }
    }
}
