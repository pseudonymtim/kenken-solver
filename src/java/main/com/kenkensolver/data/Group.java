package com.kenkensolver.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kenkensolver.solver.Utils;

public class Group {
	private final int result;
	private final Operation operation;
	private final Set<Position> positions;
	private final Set<Cell> cells;
	private Set<List<Integer>> possibleSolutions;
	
	public Group(int res, Operation op, Set<Position> pos) {
		result = res;
		operation = op;
		positions = pos;
		cells = new HashSet<Cell>();
		possibleSolutions = new HashSet<List<Integer>>();
	}
	
	public Group(int res, Operation op, Position... pos) {
		result = res;
		operation = op;
		positions = new HashSet<Position>();
		cells = new HashSet<Cell>();
		possibleSolutions = new HashSet<List<Integer>>();
		
		for (Position p : pos) {
			positions.add(p);
		}
	}

	public int getResult() {
		return result;
	}

	public Operation getOperation() {
		return operation;
	}
	
	public Set<Position> getPositions() {
		return positions;
	}

	public Set<List<Integer>> getPossibleSolutions() {
		return possibleSolutions;
	}
	
	public void removePosition(Position p) {
		positions.remove(p);
	}

	public void removeCell(Cell c) {
		cells.remove(c);
	}

	public void addCell(Cell c) {
		cells.add(c);
	}

	public Position getMostTopLeftPosition() {
		Position mostTopLeft = null;
		
		for (Position p : positions) {
			if (mostTopLeft == null) {
				mostTopLeft = p;
			}
			else if (p.getRowIndex() < mostTopLeft.getRowIndex()) {
				mostTopLeft = p;
			}
			else if (p.getRowIndex() == mostTopLeft.getRowIndex()
					&& p.getColIndex() < mostTopLeft.getColIndex()) {
				mostTopLeft = p;
			}
		}
		
		return mostTopLeft;
	}

	public void generatePossibleSolutions() {
		
		Set<List<Integer>> possibleGroupSolutions = new HashSet<List<Integer>>();
		
		for (Cell c : cells) {
			possibleGroupSolutions = Utils.crossProduct(
					possibleGroupSolutions,
					c.getPossibleValuesAsSetOfLists()); 
		}
		
		possibleGroupSolutions = Utils.removeDuplicateListsIgnoreOrdering(possibleGroupSolutions);
		
		// validate each solution against the result and operation
		Set<List<Integer>> validGroupSolutions = new HashSet<List<Integer>>();
		
		for (List<Integer> possibleGroupSolution : possibleGroupSolutions) {
			if (operation.isValidSolution(result, possibleGroupSolution)) {
				validGroupSolutions.add(possibleGroupSolution);
			}
		}
		
		// TODO: Try each of the solutions out against the layout of the group
		//  -- Can duplicates in the solution be tolerated (i.e. >1 row or col)
		//  -- Even if dups can be tolerated by the shape, does the possible values of
		//     the cell allow for that solution to be laid out in that manor
		//
		//  >> Have a concept of "is a solution valid for a group given both its result/op,
		//     its shape and its possible cell values". This will give you the true answer
		//     as to whether a solution is actually valid.
		//
		//  >> Once you have truly valid solutions, then start looking for things like
		//     "then 3 can only be in one column/row in this group, so it can't be a
		//     possibility in any other cells outside this group in the same column/row.
		//
		//  >> As cells get solved, remove them from the group they are in and change the
		//     result the group has. That way a new set of possible solutions can be generated
		//     for the remaining unsolved cells and the idea of "these 2 cells must be these 2
		//     values". Must also preserve the original format for printing later.
		//   > Generally, remove cells from groups when the cell has been solved and adjust the
		//     group result, and when a whole group is solved remove that group from the list of
		//     bespoke groups because it no longer requires any compute power.
		
		possibleSolutions = validGroupSolutions;
	}
	
}
