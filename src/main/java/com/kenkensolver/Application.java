package com.kenkensolver;

import java.util.ArrayList;
import java.util.List;

import com.kenkensolver.data.Puzzle;
import com.kenkensolver.solver.BasicSolver;

public class Application {
	
	public static void main(String[] args) {

		List<Puzzle> puzzles = new ArrayList<>();
		
		puzzles.add(PuzzleDefs.getPuzzle1());
		puzzles.add(PuzzleDefs.getPuzzle2());
		puzzles.add(PuzzleDefs.getPuzzle3());
		puzzles.add(PuzzleDefs.getPuzzle4());
		puzzles.add(PuzzleDefs.getPuzzle5());
		
		BasicSolver solver = new BasicSolver();
		
		for (Puzzle puzzle : puzzles) {
			long start = System.currentTimeMillis();
			
			solver.solve(puzzle);
			
			long end = System.currentTimeMillis();
			
			System.out.println("Puzzle " + (puzzles.indexOf(puzzle)+1) + " "
					+ (puzzle.isSolved() ? "" : "not ") + "solved! "
					+ "Took " + (end-start)/1000.0 + " seconds");
			System.out.println(puzzle.toStringAscii());
		}
		
		System.out.println("End of the puzzles.");
		

	}
}
