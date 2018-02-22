package com.maerskdigital.task.domain;

import org.springframework.data.annotation.Id;

public class SolutionResponse {
	
	@Id
	private String task;

    private Problem problem;

    private Solution solution;

    public String getTask ()
    {
        return task;
    }

    public void setTask (String task)
    {
        this.task = task;
    }

    public Problem getProblem ()
    {
        return problem;
    }

    public void setProblem (Problem problem)
    {
        this.problem = problem;
    }

    public Solution getSolution ()
    {
        return solution;
    }

    public void setSolution (Solution solution)
    {
        this.solution = solution;
    }

    @Override
    public String toString()
    {
        return "SolutionResponse [task = "+task+", problem = "+problem+", solution = "+solution+"]";
    }

}
