package com.kenkensolver;

import com.kenkensolver.data.Puzzle;
import com.kenkensolver.solver.BasicSolver;

public class Application {
	
	public static void main(String[] args) {

		Puzzle puzzle = PuzzleDefs.getPuzzle1();
		
		System.out.println(puzzle.toStringAscii());
		
		BasicSolver solver = new BasicSolver();
		
		long start = System.currentTimeMillis();
		
		solver.solve(puzzle);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Puzzle Solved!");
		System.out.println("Took " + (end-start)/1000.0 + " seconds");

	}
}
