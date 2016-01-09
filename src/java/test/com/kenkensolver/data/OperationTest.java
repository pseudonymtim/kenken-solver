package com.kenkensolver.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperationTest {

	@Test
	public void testAdd() {
		assertTrue(Operation.ADD.isValidSolution(7, 3,2,1,1));
		assertTrue(Operation.ADD.isValidSolution(7, 1,2,3,1));
		assertTrue(Operation.ADD.isValidSolution(7, 3,4));
		assertTrue(Operation.ADD.isValidSolution(7, 7));
		
		assertFalse(Operation.ADD.isValidSolution(8, 3,2,1,1));
		assertFalse(Operation.ADD.isValidSolution(8, 1,2,3,1));
		assertFalse(Operation.ADD.isValidSolution(8, 3,4));
		assertFalse(Operation.ADD.isValidSolution(8, 7));
	}
	
	@Test
	public void testSubtract() {
		assertTrue(Operation.SUBTRACT.isValidSolution(5, 6,1));
		assertTrue(Operation.SUBTRACT.isValidSolution(5, 1,6));
		assertTrue(Operation.SUBTRACT.isValidSolution(5, 2,9,1,1));
		assertTrue(Operation.SUBTRACT.isValidSolution(5, 5));
		
		assertFalse(Operation.SUBTRACT.isValidSolution(6, 6,1));
		assertFalse(Operation.SUBTRACT.isValidSolution(6, 1,6));
		assertFalse(Operation.SUBTRACT.isValidSolution(6, 2,9,1,1));
		assertFalse(Operation.SUBTRACT.isValidSolution(6, 5));
	}
	
	@Test
	public void testMultiply() {
		assertTrue(Operation.MULTIPLY.isValidSolution(24, 3,4,2));
		assertTrue(Operation.MULTIPLY.isValidSolution(24, 12,2));
		assertTrue(Operation.MULTIPLY.isValidSolution(24, 4,2,3,1,1,1));
		assertTrue(Operation.MULTIPLY.isValidSolution(24, 24));
		
		assertFalse(Operation.MULTIPLY.isValidSolution(23, 3,4,2));
		assertFalse(Operation.MULTIPLY.isValidSolution(23, 12,2));
		assertFalse(Operation.MULTIPLY.isValidSolution(23, 4,2,3,1,1,1));
		assertFalse(Operation.MULTIPLY.isValidSolution(23, 24));
	}
	
	@Test
	public void testDivide() {
		assertTrue(Operation.DIVIDE.isValidSolution(2, 6,3,1));
		assertTrue(Operation.DIVIDE.isValidSolution(2, 3,1,6));
		assertTrue(Operation.DIVIDE.isValidSolution(2, 2,4));
		assertTrue(Operation.DIVIDE.isValidSolution(2, 2));
		
		assertFalse(Operation.DIVIDE.isValidSolution(3, 6,3,1));
		assertFalse(Operation.DIVIDE.isValidSolution(3, 3,1,6));
		assertFalse(Operation.DIVIDE.isValidSolution(3, 2,4));
		assertFalse(Operation.DIVIDE.isValidSolution(3, 2));
		
		assertFalse(Operation.DIVIDE.isValidSolution(2, 5,3));
		assertTrue(Operation.DIVIDE.isValidSolution(1, 1,1));
		assertFalse(Operation.DIVIDE.isValidSolution(3, 7,2));
		assertFalse(Operation.DIVIDE.isValidSolution(2, 9,2,2));
	}
	
	@Test
	public void testNone() {
		assertTrue(Operation.NONE.isValidSolution(2, 2));
		assertTrue(Operation.NONE.isValidSolution(2, 3));
		assertTrue(Operation.NONE.isValidSolution(-1, 4));
		
		assertFalse(Operation.NONE.isValidSolution(2, 2,3));
		assertFalse(Operation.NONE.isValidSolution(3, 6,3,1));
	}
	
}
