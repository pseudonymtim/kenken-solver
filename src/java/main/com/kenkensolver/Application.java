package com.kenkensolver;

import com.kenkensolver.data.Puzzle;
import com.kenkensolver.data.Operation;
import com.kenkensolver.data.Position;
import com.kenkensolver.solver.BasicSolver;

public class Application {
	
	public static void main(String[] args) {

		// construct
		// verify that current solution is correct
		// verify that the partial solution is correct within the constraints
		//
		
		Puzzle puzzle = new Puzzle.Builder()
				.addGroup(36, Operation.MULTIPLY, 
						Position.getInstance(0, 0),
						Position.getInstance(1, 0),
						Position.getInstance(1, 1))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(0, 1),
						Position.getInstance(0, 2))
				.addGroup(4, Operation.NONE, 
						Position.getInstance(1, 2))
				.addGroup(11, Operation.ADD, 
						Position.getInstance(0, 3),
						Position.getInstance(1, 3),
						Position.getInstance(1, 4))
				.addGroup(5, Operation.SUBTRACT, 
						Position.getInstance(0, 4),
						Position.getInstance(0, 5))
				.addGroup(13, Operation.ADD, 
						Position.getInstance(1, 5),
						Position.getInstance(2, 5),
						Position.getInstance(3, 5),
						Position.getInstance(4, 5))
				.addGroup(1, Operation.NONE, 
						Position.getInstance(2, 0))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(2, 1),
						Position.getInstance(2, 2))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(2, 3),
						Position.getInstance(2, 4))
				.addGroup(10, Operation.ADD, 
						Position.getInstance(3, 0),
						Position.getInstance(3, 1),
						Position.getInstance(4, 1))
				.addGroup(72, Operation.MULTIPLY, 
						Position.getInstance(3, 2),
						Position.getInstance(4, 2),
						Position.getInstance(4, 3))
				.addGroup(3, Operation.ADD, 
						Position.getInstance(3, 3),
						Position.getInstance(3, 4))
				.addGroup(3, Operation.NONE, 
						Position.getInstance(4, 4))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(4, 0),
						Position.getInstance(5, 0))
				.addGroup(3, Operation.NONE, 
						Position.getInstance(5, 1))
				.addGroup(6, Operation.ADD, 
						Position.getInstance(5, 2),
						Position.getInstance(5, 3))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(5, 4),
						Position.getInstance(5, 5))
				.build();
		
		System.out.println(puzzle.toStringAscii());
		
		BasicSolver solver = new BasicSolver();
		
		solver.solve(puzzle);
		
		System.out.println(puzzle.toStringAscii());
		
		
		// get the builder
		// set the size
		// add cell with value
		// add group with operation, result and cells
		// 
	}
}
