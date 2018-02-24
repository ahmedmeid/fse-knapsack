package com.maerskdigital.task.domain;

/**
 * Domain object for knapsack optimization tasks
 * @author Ahmed Eid
 * 
 */
public class Tasks {

	private Task[] submitted;

    private Task[] started;

    private Task[] completed;

    public Task[] getSubmitted ()
    {
        return submitted;
    }

    public void setSubmitted (Task[] submitted)
    {
        this.submitted = submitted;
    }

    public Task[] getStarted ()
    {
        return started;
    }

    public void setStarted (Task[] started)
    {
        this.started = started;
    }

    public Task[] getCompleted ()
    {
        return completed;
    }

    public void setCompleted (Task[] completed)
    {
        this.completed = completed;
    }

    @Override
    public String toString()
    {
        return "Tasks [submitted = "+submitted+", started = "+started+", completed = "+completed+"]";
    }
}
