package com.maerskdigital.task.domain;
/**
 * Domain object for knapsack problem
 * @author Ahmed Eid
 * 
 */
public class Problem
{
    private Integer[] values;

    private Integer[] weights;

    private Integer capacity;

    public Integer[] getValues ()
    {
        return values;
    }

    public void setValues (Integer[] values)
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