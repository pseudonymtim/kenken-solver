package com.kenkensolver.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.kenkensolver.solver.Utils;

public class BespokeGroup extends Group {
	private final int result;
	private final Operation operation;
	
	// TODO: I think possible solutions var needs to be moved to the super class????
	private Set<List<Integer>> possibleSolutions;
	
	// TODO The issue is that the row and cell groups are all fucked up
	// they are missing most of their entries, which explains why some of the
	// cells are not being updated.
	
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
		// This is where possible values get eliminated from rows and cols if they must
		// be in that row/col for a given group 
		//
		
		// Only want to know about values that are guaranteed to be in the group
		updatePossibleCellValues(validGroupSolutions);
		
		
		
		//
		// TODO: As below
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
		//
		//  >> Don't forget about somehow detecting that a cell *must* be a certain value
		//     and then updating all other cells in that row/column.
		
		possibleSolutions = validGroupSolutions;
	}
	
	@Override
	public boolean isSolved() {
		return possibleSolutions.size() == 1 && super.isSolved();
	}

	public void refineSolution() {
		
		// Check if group is already solved
		if (isSolved()) {
			return;
		}
		
		Set<List<Integer>> validGroupSolutions = new HashSet<List<Integer>>();
		Set<Integer> possibleCellValues = new HashSet<Integer>();
		
		for (List<Integer> possibleSolution : possibleSolutions) {
			if (isSolutionValid(possibleSolution)) {
				validGroupSolutions.add(possibleSolution);
				possibleCellValues.addAll(possibleSolution);
			}
		}
		
		// Update possible cell values with results of possibleSolutions
		for (Cell cell : getCells()) {
			cell.removeAllValuesNotIn(possibleCellValues);
		}
		
		updatePossibleCellValues(validGroupSolutions);
		
		possibleSolutions = validGroupSolutions;
		
		// per possible solution
		
		// work out if that solution is viable with:
		// current cell possible values
		// the shape of the group
		
		
		// for each possible cell value in the group (and the group only has one solution)\\
		//
		// see if that cell value only appears in one row or column
		// then update row/column cells accordingly
		
		
		
		
	}

	private void updatePossibleCellValues(Set<List<Integer>> validGroupSolutions) {
		Set<Integer> guaranteedValuesForGroup = Utils.getIntersection(validGroupSolutions);
		
		for (Integer value : guaranteedValuesForGroup) {
			List<Cell> cellsWithValueAsPossibility = new ArrayList<Cell>();
			
			int minRow = -1;
			int maxRow = -1;
			int minCol = -1;
			int maxCol = -1;
			
			for (Cell c : getCells()) {
				if (c.getPossibleValues().contains(value)) {
					
					if (minRow==-1 || minRow > c.getPosition().getRowIndex()) {
						minRow = c.getPosition().getRowIndex();
					}
					
					if (maxRow==-1 || maxRow < c.getPosition().getRowIndex()) {
						maxRow = c.getPosition().getRowIndex();
					}
					
					if (minCol==-1 || minCol > c.getPosition().getColIndex()) {
						minCol = c.getPosition().getColIndex();
					}
					
					if (maxCol==-1 || maxCol < c.getPosition().getColIndex()) {
						maxCol = c.getPosition().getColIndex();
					}
					
					cellsWithValueAsPossibility.add(c);
				}
			}
			
			boolean valueOnlyInOneRow = minRow==maxRow;
			boolean valueOnlyInOneCol = minCol==maxCol;
			
			if (valueOnlyInOneRow) {
				// remove value as possibility from other cells in that row.
				Set<Cell> cellsInSameRow = 
						cellsWithValueAsPossibility.iterator().next().getRowGroup().getCells();
				
				cellsInSameRow.removeAll(cellsWithValueAsPossibility);
				
				for (Cell c : cellsInSameRow) {
					c.removeValueFromPossible(value);
				}
			}
			
			if (valueOnlyInOneCol) {
				// remove value as possibility from other cells in that row.
				Set<Cell> cellsInSameCol = 
						cellsWithValueAsPossibility.iterator().next().getColumnGroup().getCells();
				
				cellsInSameCol.removeAll(cellsWithValueAsPossibility);
				
				for (Cell c : cellsInSameCol) {
					c.removeValueFromPossible(value);
				}
			}
			
			
		}
	}
	
}
