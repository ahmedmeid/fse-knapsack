package com.maerskdigital.task.domain;

import java.util.Arrays;



/**
 * Domain object for knapsack problem
 * @author Ahmed Eid
 * 
 */
public class Problem
{
    private int[] values;

    private int[] weights;

    private int capacity;
    
    public Problem() {
    	
    }
    
    public Problem(int capacity, int[] weights, int[] values) {
    	 this.capacity = capacity;
    	 this.weights = weights;
    	 this.values = values;
    }

    public int[] getValues ()
    {
        return values;
    }

    public void setValues (int[] values)
    {
        this.values = values;
    }

    public int[] getWeights ()
    {
        return weights;
    }

    public void setWeights (int[] weights)
    {
        this.weights = weights;
    }

    public int getCapacity ()
    {
        return capacity;
    }

    public void setCapacity (int capacity)
    {
        this.capacity = capacity;
    }

    @Override
    public String toString()
    {
        return "Problem [values = "+Arrays.toString(values)+", weights = "+Arrays.toString(weights)+", capacity = "+capacity+"]";
    }
}