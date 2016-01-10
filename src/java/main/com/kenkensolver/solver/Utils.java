package com.kenkensolver.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
	
	public static Set<List<Integer>> crossProduct(Set<List<Integer>> set1, Set<List<Integer>> set2) {
		
		Set<List<Integer>> combos = new HashSet<List<Integer>>();
		
		if (set1.isEmpty()) {
			for (List<Integer> list2 : set2) {
				combos.add(list2);
			}
		}
		else if (set2.isEmpty()) {
			for (List<Integer> list1 : set1) {
				combos.add(list1);
			}
		}
		else {
			for (List<Integer> list1 : set1) {
				for (List<Integer> list2 : set2) {
					List<Integer> newList = new ArrayList<Integer>();
					newList.addAll(list1);
					newList.addAll(list2);
					combos.add(newList);
				}
			}
		}
		
		return combos;
	}

	public static Set<List<Integer>> removeDuplicateListsIgnoreOrdering(
			Set<List<Integer>> setOfLists) {
		return removeDuplicateLists(setOfLists, true);
	}

	public static Set<List<Integer>> removeDuplicateLists(
			Set<List<Integer>> setOfLists) {
		return removeDuplicateLists(setOfLists, false);
	}

	protected static Set<List<Integer>> removeDuplicateLists(
			Set<List<Integer>> setOfLists, boolean ignoreOrdering) {
		Set<List<Integer>> noDupsSet = new HashSet<List<Integer>>();
		
		for (List<Integer> list : setOfLists) {
			if (!alreadyIncluded(noDupsSet, list, ignoreOrdering)) {
				noDupsSet.add(list);
			}
		}
		
		return noDupsSet;
	}

	protected static boolean alreadyIncluded(Set<List<Integer>> noDupsSet, List<Integer> list, 
			boolean ignoreOrdering) {
		// There's nothing in the no dups set
		if (noDupsSet == null || noDupsSet.size() == 0) {
			return false;
		}
		
		for (List<Integer> listInSet : noDupsSet) {
			
			if (list == null) {
				// Make sure that you can assert that a null list is in the set
				if (listInSet == null) {
					return true;
				}
			}
			else if (listInSet != null && listInSet.size() == list.size()) {
				
				if (ignoreOrdering) {
					Collections.sort(list);
					Collections.sort(listInSet);
				}
				
				boolean areListsEqual = true;
				
				for (int i=0; i<list.size(); i++) {
					
					// TODO: do we need the null checking below? not sure if the sort above will
					// fail or not if there are nulls in the list.
					
					if (list.get(i) == null) {
						if (listInSet.get(i) != null) {
							areListsEqual = false;
						}
					}
					else if (listInSet.get(i) == null) {
						if (list.get(i) != null) {
							areListsEqual = false;
						}
					}
					else {
						areListsEqual &= list.get(i).equals(listInSet.get(i));
					}
					
				}
				
				if (areListsEqual) {
					return true;
				}
			}
		}
		
		return false;
	}

	public static Set<List<Integer>> generateAllUniqueOrderings(List<Integer> list) {
		Set<List<Integer>> allUniqueOrderings = new HashSet<List<Integer>>();
		
		if (list.size() == 1) {
			allUniqueOrderings.add(list);
		}
		else {
			for (int i=0; i<list.size(); i++) {
				
				Integer value = list.get(i);
				
				List<Integer> reducedList = new ArrayList<Integer>();
				
				// Add all elements before the current element to the reduced list
				if (i > 0) {
					reducedList.addAll(list.subList(0, i));
				}
				
				// Add all elements after the current element to the reduced list
				if (i < list.size()-1) {
					reducedList.addAll(list.subList(i+1, list.size()));
				}
				
				Set<List<Integer>> setOfSubOrderings = generateAllUniqueOrderings(reducedList);
				
				for (List<Integer> subOrdering : setOfSubOrderings) {
					subOrdering.add(value);
					allUniqueOrderings.add(subOrdering);
				}
				
			}
		}

		return removeDuplicateLists(allUniqueOrderings); 
	}

	public static Set<Integer> getIntersection(Set<List<Integer>> setOfLists) {
		Set<Integer> intersection = new HashSet<Integer>();
		
		if (setOfLists == null || setOfLists.isEmpty()) {
			return intersection;
		}
		
		intersection.addAll(setOfLists.iterator().next());
		
		for (List<Integer> list : setOfLists) {
			intersection = getIntersection(intersection, list);
		}
		
		return intersection;
	}
	
	protected static Set<Integer> getIntersection(Collection<Integer> list1, Collection<Integer> list2) {
		Set<Integer> intersection = new HashSet<Integer>();
		
		if (list1 != null && list2 != null && !list1.isEmpty() && !list2.isEmpty()) {
			for (Integer value : list1) {
				if (list2.contains(value)) {
					intersection.add(value);
				}
			}
		}
		
		return intersection;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
