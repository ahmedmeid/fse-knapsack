package com.maerskdigital.task.optimizer;

import com.maerskdigital.task.domain.Problem;
import com.maerskdigital.task.domain.Solution;

public interface KnapsackOptimizer {
	
	public Solution solve(Problem problem);
}
