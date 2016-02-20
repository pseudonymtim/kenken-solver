package com.kenkensolver.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testEqualsWithNull() {
		Position p1 = new Position(1, 1);
		assertFalse(p1.equals(null));
	}
	
	@Test
	public void testEqualsWithString() {
		Position p1 = new Position(1, 1);
		assertFalse(p1.equals("Position"));
	}
	
	@Test
	public void testEqualsAndHashCodeWithDifferentPosition1() {
		Position p1 = new Position(1, 2);
		Position p2 = new Position(3, 2);
		assertFalse(p1.equals(p2));
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}
	
	@Test
	public void testEqualsAndHashCodeWithDifferentPosition2() {
		Position p1 = new Position(1, 2);
		Position p2 = new Position(1, 4);
		assertFalse(p1.equals(p2));
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}
	
	@Test
	public void testEqualsAndHashCodeWithDifferentPosition3() {
		Position p1 = new Position(1, 2);
		Position p2 = new Position(3, 4);
		assertFalse(p1.equals(p2));
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}
	
	@Test
	public void testEqualsAndHashCodeWithSamePosition() {
		Position p1 = new Position(1, 2);
		Position p2 = new Position(1, 2);
		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());
	}

}
