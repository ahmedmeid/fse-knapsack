package com.maerskdigital.task.domain;

/**
 * Domain object for knapsack problem request
 * @author Ahmed Eid
 * 
 */
public class ProblemRequest {
	
	private Problem problem;

    public Problem getProblem ()
    {
        return problem;
    }

    public void setProblem (Problem problem)
    {
        this.problem = problem;
    }

    @Override
    public String toString()
    {
        return "ProblemRequest [problem = "+problem+"]";
    }

}
