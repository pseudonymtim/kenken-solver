package com.kenkensolver;

import com.kenkensolver.data.Operation;
import com.kenkensolver.data.Position;
import com.kenkensolver.data.Puzzle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PuzzleDefs {
	
	public static Puzzle getPuzzle1() {
		return new Puzzle.Builder()
				.addCage(36, Operation.MULTIPLY, 
						new Position(0, 0),
						new Position(1, 0),
						new Position(1, 1))
				.addCage(2, Operation.SUBTRACT, 
						new Position(0, 1),
						new Position(0, 2))
				.addCage(4, Operation.NONE, 
						new Position(1, 2))
				.addCage(11, Operation.ADD, 
						new Position(0, 3),
						new Position(1, 3),
						new Position(1, 4))
				.addCage(5, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(0, 5))
				.addCage(13, Operation.ADD, 
						new Position(1, 5),
						new Position(2, 5),
						new Position(3, 5),
						new Position(4, 5))
				.addCage(1, Operation.NONE, 
						new Position(2, 0))
				.addCage(3, Operation.SUBTRACT, 
						new Position(2, 1),
						new Position(2, 2))
				.addCage(2, Operation.DIVIDE, 
						new Position(2, 3),
						new Position(2, 4))
				.addCage(10, Operation.ADD, 
						new Position(3, 0),
						new Position(3, 1),
						new Position(4, 1))
				.addCage(72, Operation.MULTIPLY, 
						new Position(3, 2),
						new Position(4, 2),
						new Position(4, 3))
				.addCage(3, Operation.ADD, 
						new Position(3, 3),
						new Position(3, 4))
				.addCage(3, Operation.NONE, 
						new Position(4, 4))
				.addCage(2, Operation.SUBTRACT, 
						new Position(4, 0),
						new Position(5, 0))
				.addCage(3, Operation.NONE, 
						new Position(5, 1))
				.addCage(6, Operation.ADD, 
						new Position(5, 2),
						new Position(5, 3))
				.addCage(2, Operation.DIVIDE, 
						new Position(5, 4),
						new Position(5, 5))
				.build();
	}
	
	public static Puzzle getPuzzle2() {
		return new Puzzle.Builder()
				.addCage(2, Operation.DIVIDE, 
						new Position(0, 0),
						new Position(1, 0))
				.addCage(2, Operation.DIVIDE, 
						new Position(0, 1),
						new Position(0, 2))
				.addCage(3, Operation.SUBTRACT, 
						new Position(1, 1),
						new Position(1, 2))
				.addCage(5,  Operation.NONE,
						new Position(0, 3))
				.addCage(5, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(0, 5))
				.addCage(3, Operation.SUBTRACT, 
						new Position(1, 3),
						new Position(2, 3))
				.addCage(2, Operation.SUBTRACT, 
						new Position(1, 4),
						new Position(2, 4))
				.addCage(1,  Operation.NONE,
						new Position(1, 5))
				.addCage(12, Operation.MULTIPLY, 
						new Position(2, 0),
						new Position(2, 1))
				.addCage(3, Operation.ADD, 
						new Position(3, 0),
						new Position(3, 1))
				.addCage(2, Operation.SUBTRACT, 
						new Position(2, 2),
						new Position(3, 2))
				.addCage(2, Operation.DIVIDE, 
						new Position(3, 3),
						new Position(3, 4))
				.addCage(3, Operation.SUBTRACT, 
						new Position(2, 5),
						new Position(3, 5))
				.addCage(5,  Operation.NONE,
						new Position(4, 0))
				.addCage(5, Operation.SUBTRACT, 
						new Position(4, 1),
						new Position(4, 2))
				.addCage(4, Operation.SUBTRACT, 
						new Position(5, 0),
						new Position(5, 1))
				.addCage(3,  Operation.NONE,
						new Position(5, 2))
				.addCage(3, Operation.DIVIDE, 
						new Position(4, 3),
						new Position(5, 3))
				.addCage(2, Operation.SUBTRACT,  
						new Position(4, 4),
						new Position(5, 4))
				.addCage(1, Operation.SUBTRACT,  
						new Position(4, 5),
						new Position(5, 5))
				.build();
	}
	
	public static Puzzle getPuzzle3() {
		return new Puzzle.Builder()
				.addCage(4, Operation.DIVIDE, 
						new Position(0, 0),
						new Position(0, 1))
				.addCage(35, Operation.MULTIPLY, 
						new Position(0, 2),
						new Position(0, 3))
				.addCage(56, Operation.MULTIPLY, 
						new Position(1, 0),
						new Position(1, 1))
				.addCage(8, Operation.SUBTRACT, 
						new Position(1, 2),
						new Position(1, 3))
				.addCage(3, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(1, 4))
				.addCage(17, Operation.ADD, 
						new Position(0, 5),
						new Position(0, 6),
						new Position(1, 5))
				.addCage(14, Operation.ADD, 
						new Position(0, 7),
						new Position(0, 8),
						new Position(1, 8))
				.addCage(288, Operation.MULTIPLY, 
						new Position(1, 6),
						new Position(1, 7),
						new Position(2, 6),
						new Position(2, 7))
				.addCage(3, Operation.DIVIDE, 
						new Position(2, 0),
						new Position(2, 1))
				.addCage(7, Operation.SUBTRACT, 
						new Position(2, 2),
						new Position(2, 3))
				.addCage(3, Operation.DIVIDE, 
						new Position(2, 4),
						new Position(3, 4))
				.addCage(12, Operation.ADD, 
						new Position(2, 5),
						new Position(3, 5),
						new Position(3, 6))
				.addCage(504, Operation.MULTIPLY, 
						new Position(2, 8),
						new Position(3, 8),
						new Position(3, 7))
				.addCage(14, Operation.ADD, 
						new Position(3, 0),
						new Position(4, 0))
				.addCage(7, Operation.ADD, 
						new Position(3, 1),
						new Position(4, 1))
				.addCage(3, Operation.SUBTRACT, 
						new Position(3, 2),
						new Position(4, 2))
				.addCage(294, Operation.MULTIPLY, 
						new Position(3, 3),
						new Position(4, 3),
						new Position(4, 4))
				.addCage(2, Operation.DIVIDE, 
						new Position(4, 5),
						new Position(4, 6))
				.addCage(8, Operation.MULTIPLY, 
						new Position(4, 7),
						new Position(4, 8))
				.addCage(112, Operation.MULTIPLY, 
						new Position(5, 0),
						new Position(5, 1),
						new Position(6, 1))
				.addCage(216, Operation.MULTIPLY, 
						new Position(5, 2),
						new Position(5, 3),
						new Position(6, 3))
				.addCage(17, Operation.ADD, 
						new Position(5, 4),
						new Position(5, 5))
				.addCage(2, Operation.DIVIDE, 
						new Position(6, 4),
						new Position(6, 5))
				.addCage(2, Operation.SUBTRACT, 
						new Position(5, 6),
						new Position(6, 6))
				.addCage(2, Operation.SUBTRACT, 
						new Position(5, 7),
						new Position(6, 7))
				.addCage(120, Operation.MULTIPLY, 
						new Position(5, 8),
						new Position(6, 8),
						new Position(7, 8),
						new Position(8, 8))
				.addCage(9, Operation.ADD, 
						new Position(7, 7),
						new Position(8, 7))
				.addCage(1, Operation.SUBTRACT, 
						new Position(7, 6),
						new Position(8, 6))
				.addCage(1, Operation.SUBTRACT, 
						new Position(7, 4),
						new Position(7, 5))
				.addCage(3, Operation.SUBTRACT, 
						new Position(8, 4),
						new Position(8, 5))
				.addCage(1, Operation.SUBTRACT, 
						new Position(7, 3),
						new Position(8, 3))
				.addCage(2, Operation.DIVIDE, 
						new Position(6, 2),
						new Position(7, 2))
				.addCage(5, Operation.NONE, 
						new Position(7, 1))
				.addCage(22, Operation.ADD, 
						new Position(6, 0),
						new Position(7, 0),
						new Position(8, 0),
						new Position(8, 1),
						new Position(8, 2))
				.build();
	}
	
	public static Puzzle getPuzzle4() {
		// puzzle 73922
		return new Puzzle.Builder()
				.addCage(1, Operation.SUBTRACT, 
						new Position(0, 0),
						new Position(0, 1))
				.addCage(5, Operation.SUBTRACT, 
						new Position(0, 2),
						new Position(1, 2))
				.addCage(240, Operation.MULTIPLY, 
						new Position(0, 3),
						new Position(1, 3),
						new Position(2, 3))
				.addCage(14, Operation.ADD, 
						new Position(1, 0),
						new Position(1, 1),
						new Position(2, 0))
				.addCage(2, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(0, 5))
				.addCage(18, Operation.MULTIPLY, 
						new Position(1, 4),
						new Position(1, 5))
				.addCage(5, Operation.SUBTRACT, 
						new Position(2, 4),
						new Position(2, 5))
				.addCage(8, Operation.SUBTRACT, 
						new Position(0, 6),
						new Position(1, 6))
				.addCage(1, Operation.SUBTRACT, 
						new Position(0, 7),
						new Position(1, 7))
				.addCage(4, Operation.DIVIDE, 
						new Position(0, 8),
						new Position(1, 8))
				.addCage(7, Operation.SUBTRACT, 
						new Position(2, 7),
						new Position(2, 8))
				.addCage(3, Operation.SUBTRACT, 
						new Position(2, 6),
						new Position(3, 6))
				.addCage(378, Operation.MULTIPLY, 
						new Position(2, 2),
						new Position(3, 2),
						new Position(3, 3))
				.addCage(288, Operation.MULTIPLY, 
						new Position(2, 1),
						new Position(3, 1),
						new Position(4, 1))
				.addCage(9, Operation.ADD, 
						new Position(3, 0),
						new Position(4, 0))
				.addCage(2, Operation.DIVIDE, 
						new Position(4, 2),
						new Position(4, 3))
				.addCage(2, Operation.SUBTRACT, 
						new Position(3, 4),
						new Position(4, 4))
				.addCage(12, Operation.ADD, 
						new Position(3, 5),
						new Position(4, 5),
						new Position(4, 6))
				.addCage(22, Operation.ADD, 
						new Position(3, 7),
						new Position(3, 8),
						new Position(4, 8),
						new Position(5, 8))
				.addCage(120, Operation.MULTIPLY, 
						new Position(4, 7),
						new Position(5, 7),
						new Position(5, 6))
				.addCage(36, Operation.MULTIPLY, 
						new Position(5, 0),
						new Position(6, 0),
						new Position(6, 1))
				.addCage(8, Operation.SUBTRACT, 
						new Position(5, 1),
						new Position(5, 2))
				.addCage(10, Operation.ADD, 
						new Position(5, 3),
						new Position(5, 4))
				.addCage(13, Operation.ADD, 
						new Position(6, 2),
						new Position(6, 3),
						new Position(6, 4))
				.addCage(1, Operation.SUBTRACT, 
						new Position(5, 5),
						new Position(6, 5))
				.addCage(504, Operation.MULTIPLY, 
						new Position(6, 6),
						new Position(7, 6),
						new Position(7, 7))
				.addCage(15, Operation.MULTIPLY, 
						new Position(6, 7),
						new Position(6, 8),
						new Position(7, 8))
				.addCage(72, Operation.MULTIPLY, 
						new Position(7, 0),
						new Position(8, 0))
				.addCage(21, Operation.ADD, 
						new Position(7, 1),
						new Position(7, 2),
						new Position(8, 1),
						new Position(8, 2))
				.addCage(3, Operation.DIVIDE, 
						new Position(7, 3),
						new Position(8, 3))
				.addCage(1, Operation.SUBTRACT, 
						new Position(7, 4),
						new Position(8, 4))
				.addCage(48, Operation.MULTIPLY, 
						new Position(7, 5),
						new Position(8, 5),
						new Position(8, 6))
				.addCage(14, Operation.ADD, 
						new Position(8, 7),
						new Position(8, 8))
				.build();
	}
	
	public static Puzzle getPuzzle5() {
		return new Puzzle.Builder()
				.addCage(35, Operation.MULTIPLY, 
						new Position(0, 0),
						new Position(1, 0))
				.addCage(2, Operation.DIVIDE, 
						new Position(0, 1),
						new Position(0, 2))
				.addCage(42, Operation.MULTIPLY, 
						new Position(0, 3),
						new Position(1, 3))
				.addCage(6, Operation.ADD, 
						new Position(0, 4),
						new Position(0, 5),
						new Position(0, 6))
				.addCage(3, Operation.SUBTRACT, 
						new Position(0, 7),
						new Position(0, 8))
				.addCage(3, Operation.SUBTRACT, 
						new Position(1, 1),
						new Position(2, 1))
				.addCage(8, Operation.SUBTRACT, 
						new Position(1, 2),
						new Position(2, 2))
				.addCage(30, Operation.ADD, 
						new Position(1, 4),
						new Position(2, 4),
						new Position(3, 4),
						new Position(4, 4))
				.addCage(14, Operation.ADD, 
						new Position(1, 5),
						new Position(1, 6),
						new Position(1, 7))
				.addCage(2, Operation.SUBTRACT, 
						new Position(1, 8),
						new Position(2, 8))
				.addCage(4, Operation.SUBTRACT, 
						new Position(2, 0),
						new Position(3, 0))
				.addCage(4, Operation.DIVIDE, 
						new Position(2, 3),
						new Position(3, 3))
				.addCage(21, Operation.ADD, 
						new Position(2, 5),
						new Position(3, 5),
						new Position(3, 6))
				.addCage(72, Operation.MULTIPLY, 
						new Position(2, 6),
						new Position(2, 7),
						new Position(3, 7))
				.addCage(18, Operation.MULTIPLY, 
						new Position(3, 1),
						new Position(4, 1))
				.addCage(45, Operation.MULTIPLY, 
						new Position(3, 2),
						new Position(4, 2),
						new Position(4, 3))
				.addCage(16, Operation.ADD, 
						new Position(3, 8),
						new Position(4, 8),
						new Position(4, 7))
				.addCage(7, Operation.SUBTRACT, 
						new Position(4, 0),
						new Position(5, 0))
				.addCage(17, Operation.ADD, 
						new Position(4, 5),
						new Position(5, 5),
						new Position(6, 5))
				.addCage(8, Operation.ADD, 
						new Position(4, 6),
						new Position(5, 6))
				.addCage(10, Operation.ADD, 
						new Position(5, 1),
						new Position(6, 1),
						new Position(7, 1))
				.addCage(13, Operation.ADD, 
						new Position(5, 2),
						new Position(6, 2),
						new Position(7, 2))
				.addCage(180, Operation.MULTIPLY, 
						new Position(5, 3),
						new Position(5, 4),
						new Position(6, 4))
				.addCage(4, Operation.DIVIDE, 
						new Position(5, 7),
						new Position(5, 8))
				.addCage(3, Operation.DIVIDE, 
						new Position(6, 0),
						new Position(7, 0))
				.addCage(80, Operation.MULTIPLY, 
						new Position(6, 3),
						new Position(7, 3),
						new Position(8, 3))
				.addCage(3, Operation.DIVIDE, 
						new Position(6, 6),
						new Position(6, 7))
				.addCage(8, Operation.NONE, 
						new Position(6, 8))
				.addCage(2, Operation.SUBTRACT, 
						new Position(7, 4),
						new Position(7, 5))
				.addCage(16, Operation.ADD, 
						new Position(7, 6),
						new Position(7, 7),
						new Position(7, 8))
				.addCage(17, Operation.ADD, 
						new Position(8, 0),
						new Position(8, 1),
						new Position(8, 2))
				.addCage(3, Operation.NONE, 
						new Position(8, 4))
				.addCage(40, Operation.MULTIPLY, 
						new Position(8, 5),
						new Position(8, 6))
				.addCage(8, Operation.SUBTRACT, 
						new Position(8, 7),
						new Position(8, 8))
				.build();
	}
	
	public static Puzzle getPuzzle6() {
		throw new NotImplementedException();
	}
	
	public static Puzzle getPuzzle7() {
		throw new NotImplementedException();
	}

}
