package com.maerskdigital.task.test;

import com.maerskdigital.task.domain.Problem;
import com.maerskdigital.task.domain.ProblemRequest;

/**
 * Builder for ProblemRequest
 * @author Ahmed Eid
 *
 */
public class ProblemRequestBuilder {
	
	private ProblemRequest problemRequest;
	private Problem problem;
	
	public ProblemRequestBuilder() {
		problemRequest = new ProblemRequest();
		problem = new Problem();
		problemRequest.setProblem(problem);
	}
	
	public ProblemRequestBuilder capacity(Integer capacity) {
		problemRequest.getProblem().setCapacity(capacity);		
		return this;
	}
	
	public ProblemRequestBuilder weights(Integer[] weights) {
		problemRequest.getProblem().setWeights(weights);		
		return this;
	}
	
	public ProblemRequestBuilder values(Integer[] values) {
		problemRequest.getProblem().setValues(values);	
		return this;
	}
	
	public ProblemRequest build() {
		return problemRequest;
	}

}
