package com.maerskdigital.task.domain;

public class Timestamps {
	
	private Long submitted;

    private Long started;

    private Long completed;

    public Long getSubmitted ()
    {
        return submitted;
    }

    public void setSubmitted (Long submitted)
    {
        this.submitted = submitted;
    }

    public Long getStarted ()
    {
        return started;
    }

    public void setStarted (Long started)
    {
        this.started = started;
    }

    public Long getCompleted ()
    {
        return completed;
    }

    public void setCompleted (Long completed)
    {
        this.completed = completed;
    }

    @Override
    public String toString()
    {
        return "Timestamps [submitted = "+submitted+", started = "+started+", completed = "+completed+"]";
    }

}
