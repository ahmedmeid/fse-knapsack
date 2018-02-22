package com.maerskdigital.task.domain;

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
        return "ClassPojo [problem = "+problem+"]";
    }

}
