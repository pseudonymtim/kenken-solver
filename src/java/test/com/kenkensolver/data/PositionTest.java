package com.kenkensolver.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testEqualsWithNull() {
		Position p1 = Position.getInstance(1, 1);
		assertFalse(p1.equals(null));
	}
	
	@Test
	public void testEqualsWithString() {
		Position p1 = Position.getInstance(1, 1);
		assertFalse(p1.equals("Position"));
	}
	
	@Test
	public void testEqualsWithDifferentPosition1() {
		Position p1 = Position.getInstance(1, 2);
		Position p2 = Position.getInstance(3, 2);
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testEqualsWithDifferentPosition2() {
		Position p1 = Position.getInstance(1, 2);
		Position p2 = Position.getInstance(1, 4);
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testEqualsWithDifferentPosition3() {
		Position p1 = Position.getInstance(1, 2);
		Position p2 = Position.getInstance(3, 4);
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testEqualsWithSamePosition() {
		Position p1 = Position.getInstance(1, 2);
		Position p2 = Position.getInstance(1, 2);
		assertTrue(p1.equals(p2));
	}

}
