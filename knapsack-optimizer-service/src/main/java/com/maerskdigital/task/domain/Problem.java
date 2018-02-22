package com.maerskdigital.task.domain;

public class Problem
{
    private Long[] values;

    private Integer[] weights;

    private Integer capacity;

    public Long[] getValues ()
    {
        return values;
    }

    public void setValues (Long[] values)
    {
        this.values = values;
    }

    public Integer[] getWeights ()
    {
        return weights;
    }

    public void setWeights (Integer[] weights)
    {
        this.weights = weights;
    }

    public Integer getCapacity ()
    {
        return capacity;
    }

    public void setCapacity (Integer capacity)
    {
        this.capacity = capacity;
    }

    @Override
    public String toString()
    {
        return "Problem [values = "+values+", weights = "+weights+", capacity = "+capacity+"]";
    }
}