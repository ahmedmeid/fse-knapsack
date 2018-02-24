package com.maerskdigital.task.optimizer;

import java.util.ArrayList;
import java.util.List;

import com.maerskdigital.task.domain.Problem;
import com.maerskdigital.task.domain.Solution;
/**
 * Knapsack dynamic programming optimizer
 * @author Ahmed Eid
 *
 */
public class DynamicPorgrammingKnapsackOptimizer implements KnapsackOptimizer{

	@Override
	public Solution solve(Problem problem) {
		
		
		Integer capacity = problem.getCapacity();
		Integer[] weights = problem.getWeights();
		Integer[] values = problem.getValues();
		
		Integer nItems = weights.length;
		
		long[][] matrix = new long[nItems+1][capacity+1];
		long[][] picks = new long[nItems+1][capacity+1];
		
		for (int i = 1; i <= nItems; i++) {

			for (int j = 0; j <= capacity; j++) {

				if (weights[i - 1] <= j) {

					matrix[i][j] = Math.max(matrix[i - 1][j] , values[i - 1] + matrix[i - 1][j - weights[i - 1]]);

					if (values[i - 1] + matrix[i - 1][j - weights[i - 1]] > matrix[i - 1][j])
						picks[i][j] = 1l;
					else
						picks[i][j] = -1l;
				} else {
					picks[i][j] = -1l;

					matrix[i][j] = matrix[i - 1][j];
				}
			}
		}
		
		Solution sol = new Solution();
		
		List<Integer> items = new ArrayList<>();
		
		Integer item = nItems;

		while (item > 0) {
			if (picks[item][capacity] == 1) {
				
				items.add(item - 1 );

				item--;

				capacity -= weights[item];
			} else {
				item--;
			}
		}
		
		Integer[] temArray = new Integer[items.size()];
		
		sol.setItems(items.toArray(temArray));
						
		return sol;
	}

}
