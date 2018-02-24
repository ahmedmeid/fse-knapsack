package com.maerskdigital.task.optimizer;

import com.maerskdigital.task.domain.Problem;
import com.maerskdigital.task.domain.Solution;
/**
 * Knapsack optimizer interface
 * @author Ahmed Eid
 *
 */
public interface KnapsackOptimizer {
	
	public Solution solve(Problem problem);
}
