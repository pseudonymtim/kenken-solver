package com.kenkensolver.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.kenkensolver.solver.Utils;

public class BespokeGroup extends Group {
	private final int result;
	private final Operation operation;
	private Set<List<Integer>> possibleSolutions;
	
	public BespokeGroup(int res, Operation op) {
		super();
		result = res;
		operation = op;
		possibleSolutions = new HashSet<List<Integer>>();
	}

	public int getResult() {
		return result;
	}

	public Operation getOperation() {
		return operation;
	}

	public Set<List<Integer>> getPossibleSolutions() {
		return possibleSolutions;
	}
	
	@Override
	public String toString() {
		StringJoiner cellsJoiner = new StringJoiner(";");
		for (Cell c : getCells()) {
			cellsJoiner.add(c.getId() + c.getPosition().toString());
		}
		
		String resultAndOp = result + operation.getOperationSymbol();
		if (resultAndOp.length() < 3) {
			resultAndOp = (resultAndOp + "   ").substring(0, 3);
		}
		
		StringJoiner solutionsJoiner = new StringJoiner("");
		for (List<Integer> possibleSolution : possibleSolutions) {
			StringJoiner solutionJoiner = new StringJoiner(",");
			for (Integer value : possibleSolution) {
				solutionJoiner.add(value.toString());
			}
			solutionsJoiner.add("(" + solutionJoiner.toString() + ")");
		}
		
		return "Group={" + resultAndOp + " "
				+ "Cells={"+ cellsJoiner.toString() + "} "
				+ "PosSolns={"+solutionsJoiner+"}}";
	}
	
	@Override
	protected boolean isSolutionValid(List<Integer> potentialGroupSolution) {
		boolean isValidArithmetically = operation.isValidSolution(result, potentialGroupSolution); 
		if (isValidArithmetically) {
			return super.isSolutionValid(potentialGroupSolution);
		}
		else {
			return false;
		}
	}

	public void generatePossibleSolutions() {
		
		Set<List<Integer>> possibleGroupSolutions = new HashSet<List<Integer>>();
		
		for (Cell c : getCells()) {
			possibleGroupSolutions = Utils.crossProduct(
					possibleGroupSolutions,
					c.getPossibleValuesAsSetOfLists()); 
		}
		
		possibleGroupSolutions = Utils.removeDuplicateListsIgnoreOrdering(possibleGroupSolutions);
		
		// validate each solution against the result and operation
		Set<List<Integer>> validGroupSolutions = new HashSet<List<Integer>>();
		Set<Integer> possibleCellValues = new HashSet<Integer>();
		
		for (List<Integer> possibleGroupSolution : possibleGroupSolutions) {
			boolean validSolution = isSolutionValid(possibleGroupSolution);
			if (validSolution) {
				validGroupSolutions.add(possibleGroupSolution);
				possibleCellValues.addAll(possibleGroupSolution);
			}
		}
		
		// Update possible cell values with results of possibleSolutions
		for (Cell cell : getCells()) {
			cell.removeAllValuesNotIn(possibleCellValues);
		}
		
		
		
		//
		// TODO: As below
		//
		
		
		
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

	public void refineSolution() {
		
		// Check if group is already solved
		if (isSolved()) {
			return;
		}
		
		// per possible solution
		
		// work out if that solution is viable with:
		// current cell possible values
		// the shape of the group
		
		
		// for each possible cell value in the group (and the group only has one solution)\\
		//
		// see if that cell value only appears in one row or column
		// then update row/column cells accordingly
		
		
		
		
	}
	
}
