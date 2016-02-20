package com.kenkensolver;

import com.kenkensolver.data.Operation;
import com.kenkensolver.data.Position;
import com.kenkensolver.data.Puzzle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PuzzleDefs {
	
	public static Puzzle getPuzzle1() {
		return new Puzzle.Builder()
				.addGroup(36, Operation.MULTIPLY, 
						new Position(0, 0),
						new Position(1, 0),
						new Position(1, 1))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(0, 1),
						new Position(0, 2))
				.addGroup(4, Operation.NONE, 
						new Position(1, 2))
				.addGroup(11, Operation.ADD, 
						new Position(0, 3),
						new Position(1, 3),
						new Position(1, 4))
				.addGroup(5, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(0, 5))
				.addGroup(13, Operation.ADD, 
						new Position(1, 5),
						new Position(2, 5),
						new Position(3, 5),
						new Position(4, 5))
				.addGroup(1, Operation.NONE, 
						new Position(2, 0))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(2, 1),
						new Position(2, 2))
				.addGroup(2, Operation.DIVIDE, 
						new Position(2, 3),
						new Position(2, 4))
				.addGroup(10, Operation.ADD, 
						new Position(3, 0),
						new Position(3, 1),
						new Position(4, 1))
				.addGroup(72, Operation.MULTIPLY, 
						new Position(3, 2),
						new Position(4, 2),
						new Position(4, 3))
				.addGroup(3, Operation.ADD, 
						new Position(3, 3),
						new Position(3, 4))
				.addGroup(3, Operation.NONE, 
						new Position(4, 4))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(4, 0),
						new Position(5, 0))
				.addGroup(3, Operation.NONE, 
						new Position(5, 1))
				.addGroup(6, Operation.ADD, 
						new Position(5, 2),
						new Position(5, 3))
				.addGroup(2, Operation.DIVIDE, 
						new Position(5, 4),
						new Position(5, 5))
				.build();
	}
	
	public static Puzzle getPuzzle2() {
		return new Puzzle.Builder()
				.addGroup(2, Operation.DIVIDE, 
						new Position(0, 0),
						new Position(1, 0))
				.addGroup(2, Operation.DIVIDE, 
						new Position(0, 1),
						new Position(0, 2))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(1, 1),
						new Position(1, 2))
				.addGroup(5,  Operation.NONE,
						new Position(0, 3))
				.addGroup(5, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(0, 5))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(1, 3),
						new Position(2, 3))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(1, 4),
						new Position(2, 4))
				.addGroup(1,  Operation.NONE,
						new Position(1, 5))
				.addGroup(12, Operation.MULTIPLY, 
						new Position(2, 0),
						new Position(2, 1))
				.addGroup(3, Operation.ADD, 
						new Position(3, 0),
						new Position(3, 1))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(2, 2),
						new Position(3, 2))
				.addGroup(2, Operation.DIVIDE, 
						new Position(3, 3),
						new Position(3, 4))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(2, 5),
						new Position(3, 5))
				.addGroup(5,  Operation.NONE,
						new Position(4, 0))
				.addGroup(5, Operation.SUBTRACT, 
						new Position(4, 1),
						new Position(4, 2))
				.addGroup(4, Operation.SUBTRACT, 
						new Position(5, 0),
						new Position(5, 1))
				.addGroup(3,  Operation.NONE,
						new Position(5, 2))
				.addGroup(3, Operation.DIVIDE, 
						new Position(4, 3),
						new Position(5, 3))
				.addGroup(2, Operation.SUBTRACT,  
						new Position(4, 4),
						new Position(5, 4))
				.addGroup(1, Operation.SUBTRACT,  
						new Position(4, 5),
						new Position(5, 5))
				.build();
	}
	
	public static Puzzle getPuzzle3() {
		return new Puzzle.Builder()
				.addGroup(4, Operation.DIVIDE, 
						new Position(0, 0),
						new Position(0, 1))
				.addGroup(35, Operation.MULTIPLY, 
						new Position(0, 2),
						new Position(0, 3))
				.addGroup(56, Operation.MULTIPLY, 
						new Position(1, 0),
						new Position(1, 1))
				.addGroup(8, Operation.SUBTRACT, 
						new Position(1, 2),
						new Position(1, 3))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(1, 4))
				.addGroup(17, Operation.ADD, 
						new Position(0, 5),
						new Position(0, 6),
						new Position(1, 5))
				.addGroup(14, Operation.ADD, 
						new Position(0, 7),
						new Position(0, 8),
						new Position(1, 8))
				.addGroup(288, Operation.MULTIPLY, 
						new Position(1, 6),
						new Position(1, 7),
						new Position(2, 6),
						new Position(2, 7))
				.addGroup(3, Operation.DIVIDE, 
						new Position(2, 0),
						new Position(2, 1))
				.addGroup(7, Operation.SUBTRACT, 
						new Position(2, 2),
						new Position(2, 3))
				.addGroup(3, Operation.DIVIDE, 
						new Position(2, 4),
						new Position(3, 4))
				.addGroup(12, Operation.ADD, 
						new Position(2, 5),
						new Position(3, 5),
						new Position(3, 6))
				.addGroup(504, Operation.MULTIPLY, 
						new Position(2, 8),
						new Position(3, 8),
						new Position(3, 7))
				.addGroup(14, Operation.ADD, 
						new Position(3, 0),
						new Position(4, 0))
				.addGroup(7, Operation.ADD, 
						new Position(3, 1),
						new Position(4, 1))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(3, 2),
						new Position(4, 2))
				.addGroup(294, Operation.MULTIPLY, 
						new Position(3, 3),
						new Position(4, 3),
						new Position(4, 4))
				.addGroup(2, Operation.DIVIDE, 
						new Position(4, 5),
						new Position(4, 6))
				.addGroup(8, Operation.MULTIPLY, 
						new Position(4, 7),
						new Position(4, 8))
				.addGroup(112, Operation.MULTIPLY, 
						new Position(5, 0),
						new Position(5, 1),
						new Position(6, 1))
				.addGroup(216, Operation.MULTIPLY, 
						new Position(5, 2),
						new Position(5, 3),
						new Position(6, 3))
				.addGroup(17, Operation.ADD, 
						new Position(5, 4),
						new Position(5, 5))
				.addGroup(2, Operation.DIVIDE, 
						new Position(6, 4),
						new Position(6, 5))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(5, 6),
						new Position(6, 6))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(5, 7),
						new Position(6, 7))
				.addGroup(120, Operation.MULTIPLY, 
						new Position(5, 8),
						new Position(6, 8),
						new Position(7, 8),
						new Position(8, 8))
				.addGroup(9, Operation.ADD, 
						new Position(7, 7),
						new Position(8, 7))
				.addGroup(1, Operation.SUBTRACT, 
						new Position(7, 6),
						new Position(8, 6))
				.addGroup(1, Operation.SUBTRACT, 
						new Position(7, 4),
						new Position(7, 5))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(8, 4),
						new Position(8, 5))
				.addGroup(1, Operation.SUBTRACT, 
						new Position(7, 3),
						new Position(8, 3))
				.addGroup(2, Operation.DIVIDE, 
						new Position(6, 2),
						new Position(7, 2))
				.addGroup(5, Operation.NONE, 
						new Position(7, 1))
				.addGroup(22, Operation.ADD, 
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
				.addGroup(1, Operation.SUBTRACT, 
						new Position(0, 0),
						new Position(0, 1))
				.addGroup(5, Operation.SUBTRACT, 
						new Position(0, 2),
						new Position(1, 2))
				.addGroup(240, Operation.MULTIPLY, 
						new Position(0, 3),
						new Position(1, 3),
						new Position(2, 3))
				.addGroup(14, Operation.ADD, 
						new Position(1, 0),
						new Position(1, 1),
						new Position(2, 0))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(0, 4),
						new Position(0, 5))
				.addGroup(18, Operation.MULTIPLY, 
						new Position(1, 4),
						new Position(1, 5))
				.addGroup(5, Operation.SUBTRACT, 
						new Position(2, 4),
						new Position(2, 5))
				.addGroup(8, Operation.SUBTRACT, 
						new Position(0, 6),
						new Position(1, 6))
				.addGroup(1, Operation.SUBTRACT, 
						new Position(0, 7),
						new Position(1, 7))
				.addGroup(4, Operation.DIVIDE, 
						new Position(0, 8),
						new Position(1, 8))
				.addGroup(7, Operation.SUBTRACT, 
						new Position(2, 7),
						new Position(2, 8))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(2, 6),
						new Position(3, 6))
				.addGroup(378, Operation.MULTIPLY, 
						new Position(2, 2),
						new Position(3, 2),
						new Position(3, 3))
				.addGroup(288, Operation.MULTIPLY, 
						new Position(2, 1),
						new Position(3, 1),
						new Position(4, 1))
				.addGroup(9, Operation.ADD, 
						new Position(3, 0),
						new Position(4, 0))
				.addGroup(2, Operation.DIVIDE, 
						new Position(4, 2),
						new Position(4, 3))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(3, 4),
						new Position(4, 4))
				.addGroup(12, Operation.ADD, 
						new Position(3, 5),
						new Position(4, 5),
						new Position(4, 6))
				.addGroup(22, Operation.ADD, 
						new Position(3, 7),
						new Position(3, 8),
						new Position(4, 8),
						new Position(5, 8))
				.addGroup(120, Operation.MULTIPLY, 
						new Position(4, 7),
						new Position(5, 7),
						new Position(5, 6))
				.addGroup(36, Operation.MULTIPLY, 
						new Position(5, 0),
						new Position(6, 0),
						new Position(6, 1))
				.addGroup(8, Operation.SUBTRACT, 
						new Position(5, 1),
						new Position(5, 2))
				.addGroup(10, Operation.ADD, 
						new Position(5, 3),
						new Position(5, 4))
				.addGroup(13, Operation.ADD, 
						new Position(6, 2),
						new Position(6, 3),
						new Position(6, 4))
				.addGroup(1, Operation.SUBTRACT, 
						new Position(5, 5),
						new Position(6, 5))
				.addGroup(504, Operation.MULTIPLY, 
						new Position(6, 6),
						new Position(7, 6),
						new Position(7, 7))
				.addGroup(15, Operation.MULTIPLY, 
						new Position(6, 7),
						new Position(6, 8),
						new Position(7, 8))
				.addGroup(72, Operation.MULTIPLY, 
						new Position(7, 0),
						new Position(8, 0))
				.addGroup(21, Operation.ADD, 
						new Position(7, 1),
						new Position(7, 2),
						new Position(8, 1),
						new Position(8, 2))
				.addGroup(3, Operation.DIVIDE, 
						new Position(7, 3),
						new Position(8, 3))
				.addGroup(1, Operation.SUBTRACT, 
						new Position(7, 4),
						new Position(8, 4))
				.addGroup(48, Operation.MULTIPLY, 
						new Position(7, 5),
						new Position(8, 5),
						new Position(8, 6))
				.addGroup(14, Operation.ADD, 
						new Position(8, 7),
						new Position(8, 8))
				.build();
	}
	
	public static Puzzle getPuzzle5() {
		return new Puzzle.Builder()
				.addGroup(35, Operation.MULTIPLY, 
						new Position(0, 0),
						new Position(1, 0))
				.addGroup(2, Operation.DIVIDE, 
						new Position(0, 1),
						new Position(0, 2))
				.addGroup(42, Operation.MULTIPLY, 
						new Position(0, 3),
						new Position(1, 3))
				.addGroup(6, Operation.ADD, 
						new Position(0, 4),
						new Position(0, 5),
						new Position(0, 6))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(0, 7),
						new Position(0, 8))
				.addGroup(3, Operation.SUBTRACT, 
						new Position(1, 1),
						new Position(2, 1))
				.addGroup(8, Operation.SUBTRACT, 
						new Position(1, 2),
						new Position(2, 2))
				.addGroup(30, Operation.ADD, 
						new Position(1, 4),
						new Position(2, 4),
						new Position(3, 4),
						new Position(4, 4))
				.addGroup(14, Operation.ADD, 
						new Position(1, 5),
						new Position(1, 6),
						new Position(1, 7))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(1, 8),
						new Position(2, 8))
				.addGroup(4, Operation.SUBTRACT, 
						new Position(2, 0),
						new Position(3, 0))
				.addGroup(4, Operation.DIVIDE, 
						new Position(2, 3),
						new Position(3, 3))
				.addGroup(21, Operation.ADD, 
						new Position(2, 5),
						new Position(3, 5),
						new Position(3, 6))
				.addGroup(72, Operation.MULTIPLY, 
						new Position(2, 6),
						new Position(2, 7),
						new Position(3, 7))
				.addGroup(18, Operation.MULTIPLY, 
						new Position(3, 1),
						new Position(4, 1))
				.addGroup(45, Operation.MULTIPLY, 
						new Position(3, 2),
						new Position(4, 2),
						new Position(4, 3))
				.addGroup(16, Operation.ADD, 
						new Position(3, 8),
						new Position(4, 8),
						new Position(4, 7))
				.addGroup(7, Operation.SUBTRACT, 
						new Position(4, 0),
						new Position(5, 0))
				.addGroup(17, Operation.ADD, 
						new Position(4, 5),
						new Position(5, 5),
						new Position(6, 5))
				.addGroup(8, Operation.ADD, 
						new Position(4, 6),
						new Position(5, 6))
				.addGroup(10, Operation.ADD, 
						new Position(5, 1),
						new Position(6, 1),
						new Position(7, 1))
				.addGroup(13, Operation.ADD, 
						new Position(5, 2),
						new Position(6, 2),
						new Position(7, 2))
				.addGroup(180, Operation.MULTIPLY, 
						new Position(5, 3),
						new Position(5, 4),
						new Position(6, 4))
				.addGroup(4, Operation.DIVIDE, 
						new Position(5, 7),
						new Position(5, 8))
				.addGroup(3, Operation.DIVIDE, 
						new Position(6, 0),
						new Position(7, 0))
				.addGroup(80, Operation.MULTIPLY, 
						new Position(6, 3),
						new Position(7, 3),
						new Position(8, 3))
				.addGroup(3, Operation.DIVIDE, 
						new Position(6, 6),
						new Position(6, 7))
				.addGroup(8, Operation.NONE, 
						new Position(6, 8))
				.addGroup(2, Operation.SUBTRACT, 
						new Position(7, 4),
						new Position(7, 5))
				.addGroup(16, Operation.ADD, 
						new Position(7, 6),
						new Position(7, 7),
						new Position(7, 8))
				.addGroup(17, Operation.ADD, 
						new Position(8, 0),
						new Position(8, 1),
						new Position(8, 2))
				.addGroup(3, Operation.NONE, 
						new Position(8, 4))
				.addGroup(40, Operation.MULTIPLY, 
						new Position(8, 5),
						new Position(8, 6))
				.addGroup(8, Operation.SUBTRACT, 
						new Position(8, 7),
						new Position(8, 8))
				.build();
	}
	
	public static Puzzle getPuzzle6() {
		return new Puzzle.Builder()
				.addGroup(13, Operation.ADD, 
						new Position(0, 0),
						new Position(0, 1),
						new Position(1, 0),
						new Position(1, 1))
				.addGroup(180, Operation.MULTIPLY, 
						new Position(0, 2),
						new Position(0, 3),
						new Position(0, 4),
						new Position(1, 3))
				.addGroup(9, Operation.ADD, 
						new Position(0, 5),
						new Position(1, 5),
						new Position(2, 5))
				.addGroup(2, Operation.NONE, 
						new Position(1, 2))
				.addGroup(20, Operation.MULTIPLY, 
						new Position(1, 4),
						new Position(2, 4))
				.addGroup(15, Operation.ADD, 
						new Position(2, 0),
						new Position(3, 0),
						new Position(4, 0))
				.addGroup(6, Operation.MULTIPLY, 
						new Position(2, 1),
						new Position(2, 2))
				.addGroup(11, Operation.ADD, 
						new Position(2, 3),
						new Position(3, 3),
						new Position(3, 2))
				.addGroup(3, Operation.NONE, 
						new Position(3, 1))
				.addGroup(9, Operation.ADD, 
						new Position(3, 4),
						new Position(3, 5),
						new Position(4, 3),
						new Position(4, 4))
				.addGroup(2, Operation.DIVIDE, 
						new Position(4, 1),
						new Position(4, 2))
				.addGroup(18, Operation.ADD, 
						new Position(4, 5),
						new Position(5, 3),
						new Position(5, 4),
						new Position(5, 5))
				.addGroup(8, Operation.ADD, 
						new Position(5, 0),
						new Position(5, 1),
						new Position(5, 2))
				.build();
	}
	
	public static Puzzle getPuzzle7() {
		throw new NotImplementedException();
	}
	
	public static Puzzle getPuzzle8() {
		throw new NotImplementedException();
	}

}
