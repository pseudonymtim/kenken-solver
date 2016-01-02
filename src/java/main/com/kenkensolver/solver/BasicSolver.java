package com.kenkensolver.solver;

import com.kenkensolver.data.Puzzle;

public class BasicSolver implements Solver {

	@Override
	public void solve(Puzzle p) {
		
	}
	
	/*
	 * GOT IT
	 * 
	 * Find possible solution(s) for a group
	 * iterate through all cells and update based on those possibilities.
	 */
	
	/*
	 * Start with puzzle
	 * Iterate through cells
	 * - populate with possible values
	 * 
	 */
	
	/*
	 * The base case to stop repeating the processing is when all the cells have only 
	 * one possible value left.
	 * 
	 * Per bespoke group
	 * - Generate all possible solutions
	 *     - Solutions have same length as number of positions in group
	 *     - Solutions have uniqueness limit based on how many rows/columns the group covers
	 * 
	 * Take group
	 * Get positions
	 * Get cells
	 * Take cross product of the possible values for each cell
	 * That will generate all possible solutions for that group
	 * - test solutions against uniqueness constraints
	 * - test solutions against operation and result
	 * - keep only those that pass both tests
	 * 
	 */

}
