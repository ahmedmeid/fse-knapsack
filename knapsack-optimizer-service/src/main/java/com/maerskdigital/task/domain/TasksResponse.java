package com.maerskdigital.task.domain;

/**
 * Domain object for knapsack optimization tasks response
 * @author Ahmed Eid
 * 
 */
public class TasksResponse {
	
	private Tasks tasks;

    public Tasks getTasks ()
    {
        return tasks;
    }

    public void setTasks (Tasks tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public String toString()
    {
        return "TasksResponse [tasks = "+tasks+"]";
    }

}
