package com.kenkensolver;

import com.kenkensolver.data.Operation;
import com.kenkensolver.data.Position;
import com.kenkensolver.data.Puzzle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PuzzleDefs {
	
	public static Puzzle getPuzzle1() {
		return new Puzzle.Builder()
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
	}
	
	public static Puzzle getPuzzle2() {
		return new Puzzle.Builder()
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(0, 0),
						Position.getInstance(1, 0))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(0, 1),
						Position.getInstance(0, 2))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(1, 1),
						Position.getInstance(1, 2))
				.addGroup(5,  Operation.NONE,
						Position.getInstance(0, 3))
				.addGroup(5, Operation.SUBTRACT, 
						Position.getInstance(0, 4),
						Position.getInstance(0, 5))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(1, 3),
						Position.getInstance(2, 3))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(1, 4),
						Position.getInstance(2, 4))
				.addGroup(1,  Operation.NONE,
						Position.getInstance(1, 5))
				.addGroup(12, Operation.MULTIPLY, 
						Position.getInstance(2, 0),
						Position.getInstance(2, 1))
				.addGroup(3, Operation.ADD, 
						Position.getInstance(3, 0),
						Position.getInstance(3, 1))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(2, 2),
						Position.getInstance(3, 2))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(3, 3),
						Position.getInstance(3, 4))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(2, 5),
						Position.getInstance(3, 5))
				.addGroup(5,  Operation.NONE,
						Position.getInstance(4, 0))
				.addGroup(5, Operation.SUBTRACT, 
						Position.getInstance(4, 1),
						Position.getInstance(4, 2))
				.addGroup(4, Operation.SUBTRACT, 
						Position.getInstance(5, 0),
						Position.getInstance(5, 1))
				.addGroup(3,  Operation.NONE,
						Position.getInstance(5, 2))
				.addGroup(3, Operation.DIVIDE, 
						Position.getInstance(4, 3),
						Position.getInstance(5, 3))
				.addGroup(2, Operation.SUBTRACT,  
						Position.getInstance(4, 4),
						Position.getInstance(5, 4))
				.addGroup(1, Operation.SUBTRACT,  
						Position.getInstance(4, 5),
						Position.getInstance(5, 5))
				.build();
	}
	
	public static Puzzle getPuzzle3() {
		return new Puzzle.Builder()
				.addGroup(4, Operation.DIVIDE, 
						Position.getInstance(0, 0),
						Position.getInstance(0, 1))
				.addGroup(35, Operation.MULTIPLY, 
						Position.getInstance(0, 2),
						Position.getInstance(0, 3))
				.addGroup(56, Operation.MULTIPLY, 
						Position.getInstance(1, 0),
						Position.getInstance(1, 1))
				.addGroup(8, Operation.SUBTRACT, 
						Position.getInstance(1, 2),
						Position.getInstance(1, 3))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(0, 4),
						Position.getInstance(1, 4))
				.addGroup(17, Operation.ADD, 
						Position.getInstance(0, 5),
						Position.getInstance(0, 6),
						Position.getInstance(1, 5))
				.addGroup(14, Operation.ADD, 
						Position.getInstance(0, 7),
						Position.getInstance(0, 8),
						Position.getInstance(1, 8))
				.addGroup(288, Operation.MULTIPLY, 
						Position.getInstance(1, 6),
						Position.getInstance(1, 7),
						Position.getInstance(2, 6),
						Position.getInstance(2, 7))
				.addGroup(3, Operation.DIVIDE, 
						Position.getInstance(2, 0),
						Position.getInstance(2, 1))
				.addGroup(7, Operation.SUBTRACT, 
						Position.getInstance(2, 2),
						Position.getInstance(2, 3))
				.addGroup(3, Operation.DIVIDE, 
						Position.getInstance(2, 4),
						Position.getInstance(3, 4))
				.addGroup(12, Operation.ADD, 
						Position.getInstance(2, 5),
						Position.getInstance(3, 5),
						Position.getInstance(3, 6))
				.addGroup(504, Operation.MULTIPLY, 
						Position.getInstance(2, 8),
						Position.getInstance(3, 8),
						Position.getInstance(3, 7))
				.addGroup(14, Operation.ADD, 
						Position.getInstance(3, 0),
						Position.getInstance(4, 0))
				.addGroup(7, Operation.ADD, 
						Position.getInstance(3, 1),
						Position.getInstance(4, 1))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(3, 2),
						Position.getInstance(4, 2))
				.addGroup(294, Operation.MULTIPLY, 
						Position.getInstance(3, 3),
						Position.getInstance(4, 3),
						Position.getInstance(4, 4))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(4, 5),
						Position.getInstance(4, 6))
				.addGroup(8, Operation.MULTIPLY, 
						Position.getInstance(4, 7),
						Position.getInstance(4, 8))
				.addGroup(112, Operation.MULTIPLY, 
						Position.getInstance(5, 0),
						Position.getInstance(5, 1),
						Position.getInstance(6, 1))
				.addGroup(216, Operation.MULTIPLY, 
						Position.getInstance(5, 2),
						Position.getInstance(5, 3),
						Position.getInstance(6, 3))
				.addGroup(17, Operation.ADD, 
						Position.getInstance(5, 4),
						Position.getInstance(5, 5))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(6, 4),
						Position.getInstance(6, 5))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(5, 6),
						Position.getInstance(6, 6))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(5, 7),
						Position.getInstance(6, 7))
				.addGroup(120, Operation.MULTIPLY, 
						Position.getInstance(5, 8),
						Position.getInstance(6, 8),
						Position.getInstance(7, 8),
						Position.getInstance(8, 8))
				.addGroup(9, Operation.ADD, 
						Position.getInstance(7, 7),
						Position.getInstance(8, 7))
				.addGroup(1, Operation.SUBTRACT, 
						Position.getInstance(7, 6),
						Position.getInstance(8, 6))
				.addGroup(1, Operation.SUBTRACT, 
						Position.getInstance(7, 4),
						Position.getInstance(7, 5))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(8, 4),
						Position.getInstance(8, 5))
				.addGroup(1, Operation.SUBTRACT, 
						Position.getInstance(7, 3),
						Position.getInstance(8, 3))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(6, 2),
						Position.getInstance(7, 2))
				.addGroup(5, Operation.NONE, 
						Position.getInstance(7, 1))
				.addGroup(22, Operation.ADD, 
						Position.getInstance(6, 0),
						Position.getInstance(7, 0),
						Position.getInstance(8, 0),
						Position.getInstance(8, 1),
						Position.getInstance(8, 2))
				.build();
	}
	
	public static Puzzle getPuzzle4() {
		return new Puzzle.Builder()
				.addGroup(1, Operation.SUBTRACT, 
						Position.getInstance(0, 0),
						Position.getInstance(0, 1))
				.addGroup(5, Operation.SUBTRACT, 
						Position.getInstance(0, 2),
						Position.getInstance(1, 2))
				.addGroup(240, Operation.MULTIPLY, 
						Position.getInstance(0, 3),
						Position.getInstance(1, 3),
						Position.getInstance(2, 3))
				.addGroup(14, Operation.ADD, 
						Position.getInstance(1, 0),
						Position.getInstance(1, 1),
						Position.getInstance(2, 0))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(0, 4),
						Position.getInstance(0, 5))
				.addGroup(18, Operation.MULTIPLY, 
						Position.getInstance(1, 4),
						Position.getInstance(1, 5))
				.addGroup(5, Operation.SUBTRACT, 
						Position.getInstance(2, 4),
						Position.getInstance(2, 5))
				.addGroup(8, Operation.SUBTRACT, 
						Position.getInstance(0, 6),
						Position.getInstance(1, 6))
				.addGroup(1, Operation.SUBTRACT, 
						Position.getInstance(0, 7),
						Position.getInstance(1, 7))
				.addGroup(4, Operation.DIVIDE, 
						Position.getInstance(0, 8),
						Position.getInstance(1, 8))
				.addGroup(7, Operation.SUBTRACT, 
						Position.getInstance(2, 7),
						Position.getInstance(2, 8))
				.addGroup(3, Operation.SUBTRACT, 
						Position.getInstance(2, 6),
						Position.getInstance(3, 6))
				.addGroup(378, Operation.MULTIPLY, 
						Position.getInstance(2, 2),
						Position.getInstance(3, 2),
						Position.getInstance(3, 3))
				.addGroup(288, Operation.MULTIPLY, 
						Position.getInstance(2, 1),
						Position.getInstance(3, 1),
						Position.getInstance(4, 1))
				.addGroup(9, Operation.ADD, 
						Position.getInstance(3, 0),
						Position.getInstance(4, 0))
				.addGroup(2, Operation.DIVIDE, 
						Position.getInstance(4, 2),
						Position.getInstance(4, 3))
				.addGroup(2, Operation.SUBTRACT, 
						Position.getInstance(3, 4),
						Position.getInstance(4, 4))
				.addGroup(12, Operation.ADD, 
						Position.getInstance(3, 5),
						Position.getInstance(4, 5),
						Position.getInstance(4, 6))
				.addGroup(22, Operation.ADD, 
						Position.getInstance(3, 7),
						Position.getInstance(3, 8),
						Position.getInstance(4, 8),
						Position.getInstance(5, 8))
				.addGroup(120, Operation.MULTIPLY, 
						Position.getInstance(4, 7),
						Position.getInstance(5, 7),
						Position.getInstance(5, 6))
				.addGroup(36, Operation.MULTIPLY, 
						Position.getInstance(5, 0),
						Position.getInstance(6, 0),
						Position.getInstance(6, 1))
				.addGroup(8, Operation.SUBTRACT, 
						Position.getInstance(5, 1),
						Position.getInstance(5, 2))
				.addGroup(10, Operation.ADD, 
						Position.getInstance(5, 3),
						Position.getInstance(5, 4))
				.addGroup(13, Operation.ADD, 
						Position.getInstance(6, 2),
						Position.getInstance(6, 3),
						Position.getInstance(6, 4))
				.addGroup(1, Operation.SUBTRACT, 
						Position.getInstance(5, 5),
						Position.getInstance(6, 5))
				.addGroup(504, Operation.MULTIPLY, 
						Position.getInstance(6, 6),
						Position.getInstance(7, 6),
						Position.getInstance(7, 7))
				.addGroup(15, Operation.MULTIPLY, 
						Position.getInstance(6, 7),
						Position.getInstance(6, 8),
						Position.getInstance(7, 8))
				.addGroup(72, Operation.MULTIPLY, 
						Position.getInstance(7, 0),
						Position.getInstance(8, 0))
				.addGroup(21, Operation.ADD, 
						Position.getInstance(7, 1),
						Position.getInstance(7, 2),
						Position.getInstance(8, 1),
						Position.getInstance(8, 2))
				.addGroup(3, Operation.DIVIDE, 
						Position.getInstance(7, 3),
						Position.getInstance(8, 3))
				.addGroup(1, Operation.SUBTRACT, 
						Position.getInstance(7, 4),
						Position.getInstance(8, 4))
				.addGroup(48, Operation.MULTIPLY, 
						Position.getInstance(7, 5),
						Position.getInstance(8, 5),
						Position.getInstance(8, 6))
				.addGroup(14, Operation.ADD, 
						Position.getInstance(8, 7),
						Position.getInstance(8, 8))
				.build();
	}
	
	public static Puzzle getPuzzle5() {
		throw new NotImplementedException();
	}
	
	public static Puzzle getPuzzle6() {
		throw new NotImplementedException();
	}
	
	public static Puzzle getPuzzle7() {
		throw new NotImplementedException();
	}
	
	public static Puzzle getPuzzle8() {
		throw new NotImplementedException();
	}

}