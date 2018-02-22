package com.maerskdigital.task.domain;

import org.springframework.data.annotation.Id;

public class Task {

	@Id
	private String task;
	
	private String status;
	
	private Timestamps timestamps;
  

    public Timestamps getTimestamps ()
    {
        return timestamps;
    }

    public void setTimestamps (Timestamps timestamps)
    {
        this.timestamps = timestamps;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getTask ()
    {
        return task;
    }

    public void setTask (String task)
    {
        this.task = task;
    }

    @Override
    public String toString()
    {
        return "Task [timestamps = "+timestamps+", status = "+status+", task = "+task+"]";
    }
}
