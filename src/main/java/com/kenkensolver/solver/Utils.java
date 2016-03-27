package com.kenkensolver.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
	
	public static <T> Set<List<T>> crossProduct(Set<List<T>> set1, Set<List<T>> set2) {
		
		Set<List<T>> combinations = new HashSet<List<T>>();
		
		if (set1 == null || set2 == null) {
			return null;
		}
		
		if (set1.isEmpty()) {
			for (List<T> list : set2) {
				combinations.add(list);
			}
		}
		else if (set2.isEmpty()) {
			for (List<T> list : set1) {
				combinations.add(list);
			}
		}
		else {
			for (List<T> listFromSet1 : set1) {
				for (List<T> listFromSet2 : set2) {
					List<T> newList = new ArrayList<T>();
					newList.addAll(listFromSet1);
					newList.addAll(listFromSet2);
					combinations.add(newList);
				}
			}
		}
		
		return combinations;
	}

	public static <T extends Comparable<T>> Set<List<T>> removeDuplicateListsIgnoreOrdering(
			Set<List<T>> setOfLists) {
		return removeDuplicateLists(setOfLists, true);
	}

	public static <T extends Comparable<T>> Set<List<T>> removeDuplicateLists(
			Set<List<T>> setOfLists) {
		return removeDuplicateLists(setOfLists, false);
	}

	protected static <T extends Comparable<T>> Set<List<T>> removeDuplicateLists(
			Set<List<T>> setOfLists, boolean ignoreOrdering) {
		Set<List<T>> noDupsSet = new HashSet<>();
		
		for (List<T> list : setOfLists) {
			if (!alreadyIncluded(noDupsSet, list, ignoreOrdering)) {
				noDupsSet.add(list);
			}
		}
		
		return noDupsSet;
	}

	protected static <T extends Comparable<T>> boolean alreadyIncluded(
			Set<List<T>> noDupsSet, List<T> list, boolean ignoreOrdering) {
		
		// There's nothing in the no dups set
		if (noDupsSet == null || noDupsSet.size() == 0) {
			return false;
		}
		
		for (List<T> listInSet : noDupsSet) {
			
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
	
	public static <T extends Comparable<T>> Set<List<T>> generateAllUniqueOrderings(
			Set<List<T>> setOfLists) {
		
		Set<List<T>> allUniqueOrderings = new HashSet<>();
		
		for (List<T> list : setOfLists) {
			allUniqueOrderings.addAll(generateAllUniqueOrderings(list));
		}
		
		return removeDuplicateLists(allUniqueOrderings);
	}

	public static <T extends Comparable<T>> Set<List<T>> generateAllUniqueOrderings(
			List<T> list) {
		
		Set<List<T>> allUniqueOrderings = new HashSet<>();
		
		if (list.size() == 1) {
			allUniqueOrderings.add(list);
		}
		else {
			for (int i=0; i<list.size(); i++) {
				
				T value = list.get(i);
				
				List<T> reducedList = new ArrayList<>();
				
				// Add all elements before the current element to the reduced list
				if (i > 0) {
					reducedList.addAll(list.subList(0, i));
				}
				
				// Add all elements after the current element to the reduced list
				if (i < list.size()-1) {
					reducedList.addAll(list.subList(i+1, list.size()));
				}
				
				Set<List<T>> setOfSubOrderings = generateAllUniqueOrderings(reducedList);
				
				for (List<T> subOrdering : setOfSubOrderings) {
					subOrdering.add(value);
					allUniqueOrderings.add(subOrdering);
				}
				
			}
		}

		return removeDuplicateLists(allUniqueOrderings);
	}

	public static <S extends Collection<T>, T> Set<T> getIntersection(Set<S> setOfLists) {
		Set<T> intersection = new HashSet<>();
		
		if (setOfLists == null || setOfLists.isEmpty()) {
			return intersection;
		}
		
		// Get the first non-null list and add its contents to the intersection
		for (S list : setOfLists) {
			if (list != null) {
				intersection.addAll(list);
			}
		}
		
		for (S list : setOfLists) {
			intersection = getIntersection(intersection, list);
		}
		
		return intersection;
	}
	
	protected static <S extends Collection<T>, T> Set<T> getIntersection(S list1, S list2) {
		Set<T> intersection = new HashSet<>();
		
		if (list1 != null && list2 != null && !list1.isEmpty() && !list2.isEmpty()) {
			for (T value : list1) {
				if (list2.contains(value)) {
					intersection.add(value);
				}
			}
		}
		
		return intersection;
	}
	
}
