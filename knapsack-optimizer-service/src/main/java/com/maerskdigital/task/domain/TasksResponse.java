package com.maerskdigital.task.domain;

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
