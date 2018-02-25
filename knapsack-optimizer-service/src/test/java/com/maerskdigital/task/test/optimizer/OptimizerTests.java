package com.maerskdigital.task.test.optimizer;

import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;

import com.maerskdigital.task.domain.Problem;
import com.maerskdigital.task.domain.Solution;
import com.maerskdigital.task.optimizer.DynamicPorgrammingKnapsackOptimizer;
import com.maerskdigital.task.optimizer.KnapsackOptimizer;
/**
 * Test cases for knapsack optimizer
 * @author Ahmed Eid
 *
 */
public class OptimizerTests {
	
	
	@Test
	public void testSolve() {
		KnapsackOptimizer optimizer = new DynamicPorgrammingKnapsackOptimizer();
		Problem problem = new Problem(60, new int[] {10, 20, 33}, new int[] {10, 3, 30});
		Solution solution = optimizer.solve(problem);
		assertArrayEquals(new int[] {0,2}, solution.getItems());
		
	}

}
