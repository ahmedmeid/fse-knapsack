package com.maerskdigital.task.domain;

public class Solution {
	
	private Long time;

    private Integer[] items;

    public Long getTime ()
    {
        return time;
    }

    public void setTime (Long time)
    {
        this.time = time;
    }

    public Integer[] getItems ()
    {
        return items;
    }

    public void setItems (Integer[] items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "Solution [time = "+time+", items = "+items+"]";
    }

}
