package com.kenkensolver.solver;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Test;

public class BasicSolverTest {
	
	private BasicSolver bs = new BasicSolver();
	
	private Set<List<Integer>> setOfLists;
	
	@After
	public void tearDown() {
//		System.out.println(setOfLists);
		setOfLists = null;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGeneratePossibleSolutions_NegativeSolutionSize() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		setOfLists = bs.generatePossibleSolutions(list, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGeneratePossibleSolutions_ZeroSolutionSize() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		setOfLists = bs.generatePossibleSolutions(list, 0);
	}
	
	@Test
	public void testGeneratePossibleSolutions_1() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		setOfLists = bs.generatePossibleSolutions(list, 1);
		assertEquals(1, setOfLists.size());
		assertEquals(1, setOfLists.iterator().next().size());
		assertEquals(1, setOfLists.iterator().next().get(0).intValue());
	}
	
	@Test
	public void testGeneratePossibleSolutions_2() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		setOfLists = bs.generatePossibleSolutions(list, 2);
		assertEquals(3, setOfLists.size());
		assertEquals(2, setOfLists.iterator().next().size());
	}
	
	@Test
	public void testGeneratePossibleSolutions_3() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		setOfLists = bs.generatePossibleSolutions(list, 3);
		assertEquals(1, setOfLists.size());
		assertEquals(3, setOfLists.iterator().next().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGeneratePossibleSolutions_4() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		setOfLists = bs.generatePossibleSolutions(list, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGeneratePossibleSolutions_5() {
		setOfLists = bs.generatePossibleSolutions(null, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGeneratePossibleSolutions_6() {
		setOfLists = bs.generatePossibleSolutions(new ArrayList<>(), 2);
	}
	
	@Test
	public void testGeneratePossibleSolutions_7() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		setOfLists = bs.generatePossibleSolutions(list, 2);
		assertEquals(4, setOfLists.size());
		assertEquals(2, setOfLists.iterator().next().size());
	}
	
	@Test
	public void testGeneratePossibleSolutions_8() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(4);
		setOfLists = bs.generatePossibleSolutions(list, 3);
		assertEquals(10, setOfLists.size());
		assertEquals(3, setOfLists.iterator().next().size());
	}
	
}
