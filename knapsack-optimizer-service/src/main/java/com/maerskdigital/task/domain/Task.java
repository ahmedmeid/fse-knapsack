package com.maerskdigital.task.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain object for knapsack optimization task
 * @author Ahmed Eid
 * 
 */
@Document
public class Task {

	@Id
	private String task;
	
	private String status;
	
	private Timestamps timestamps;
	
	public Task() {
		
	}
  
	public Task(String task, Timestamps timestamps) {
		this.task = task;
		status = "started";
		this.timestamps = timestamps;		
	}

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
        return "Task [task = "+task+", status = "+status+", timestamps = "+timestamps+"]";
    }
}
