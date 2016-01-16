package com.kenkensolver.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class UtilsTest {
	
	@Test
	public void testGetIntersection_NullAndEmpty() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		setOfLists.add(null);
		setOfLists.add(list1);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(0, intersection.size());
	}
	
	@Test
	public void testGetIntersection_NullAndPopulated() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		
		setOfLists.add(null);
		setOfLists.add(list1);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(0, intersection.size());
	}
	
	@Test
	public void testGetIntersection_EmptyAndEmpty() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(0, intersection.size());
	}
	
	@Test
	public void testGetIntersection_EmptyAndPopulated() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(0, intersection.size());
	}
	
	@Test
	public void testGetIntersection_TwoIdenticalLists() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		
		setOfLists.add(list2);
		setOfLists.add(list2);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(2, intersection.size());
		assertTrue(intersection.contains(1));
		assertTrue(intersection.contains(2));
	}
	
	@Test
	public void testGetIntersection_TwoPopulatedListsNoIntersection() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(0, intersection.size());
	}
	
	@Test
	public void testGetIntersection_TwoPopulatedListsSomeIntersection1() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(1, intersection.size());
		assertTrue(intersection.contains(1));
	}
	
	@Test
	public void testGetIntersection_TwoPopulatedListsSomeIntersection2() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		list2.add(3);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(1, intersection.size());
		assertTrue(intersection.contains(2));
	}

	@Test
	public void testGetIntersection_TwoPopulatedListsSomeIntersection3() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(2, intersection.size());
		assertTrue(intersection.contains(3));
		assertTrue(intersection.contains(4));
	}
	
	@Test
	public void testGetIntersection_ThreePopulatedListsNoIntersection() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(3);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		setOfLists.add(list3);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(0, intersection.size());
	}
	
	@Test
	public void testGetIntersection_ThreePopulatedListsSomeIntersection1() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		list2.add(3);
		
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(3);
		list3.add(4);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		setOfLists.add(list3);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(0, intersection.size());
	}
	
	@Test
	public void testGetIntersection_ThreePopulatedListsSomeIntersection2() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		list2.add(3);
		
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(2);
		list3.add(3);
		list3.add(4);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		setOfLists.add(list3);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(1, intersection.size());
		assertTrue(intersection.contains(2));
	}
	
	@Test
	public void testGetIntersection_ThreePopulatedListsSomeIntersection3() {
		Set<List<Integer>> setOfLists = new HashSet<List<Integer>>();
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		list2.add(3);
		list2.add(4);
		
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(2);
		list3.add(3);
		list3.add(5);
		
		setOfLists.add(list1);
		setOfLists.add(list2);
		setOfLists.add(list3);
		
		Set<Integer> intersection = Utils.getIntersection(setOfLists);
		
		assertNotNull(intersection);
		assertEquals(2, intersection.size());
		assertTrue(intersection.contains(2));
		assertTrue(intersection.contains(3));
	}

}
