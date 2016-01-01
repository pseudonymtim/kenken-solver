package com.kenkensolver.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperationTest {

	@Test
	public void testAdd() {
		Operation op = Operation.ADD;
		assertEquals(7, op.calc(3,2,1,1));
	}
	
	@Test
	public void testSubtract() {
		Operation op = Operation.SUBTRACT;
		assertEquals(5, op.calc(6,1));
	}
	
	@Test
	public void testMultiply() {
		Operation op = Operation.MULTIPLY;
		assertEquals(24, op.calc(3,4,2));
	}
	
	@Test
	public void testDivideWholeNumber() {
		Operation op = Operation.DIVIDE;
		assertEquals(2, op.calc(6,3));
	}
	
	@Test
	public void testDivideNonZeroRemainder() {
		Operation op = Operation.DIVIDE;
		assertEquals(0, op.calc(6,4));
	}
	
	@Test
	public void testDivideNonZeroRemainderWith5Args() {
		Operation op = Operation.DIVIDE;
		assertEquals(0, op.calc(12,4,2,1,1));
	}
	
}
