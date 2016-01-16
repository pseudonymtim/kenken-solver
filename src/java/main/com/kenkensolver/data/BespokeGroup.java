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
		
		for (Cell c : getCells()) {
			possibleSolutions = Utils.crossProduct(
					possibleSolutions,
					c.getPossibleValuesAsSetOfLists()); 
		}
		
		possibleSolutions = Utils.removeDuplicateListsIgnoreOrdering(possibleSolutions);
		
	}
	
	@Override
	public boolean isSolved() {
		return possibleSolutions.size() == 1 && super.isSolved();
	}

	@Override
	public void refineSolutionSpace(Puzzle p) {
		
		// Check if group is already solved
		if (isSolved()) {
			return;
		}
		
		Set<List<Integer>> validGroupSolutions = new HashSet<List<Integer>>();
		Set<Integer> possibleCellValues = new HashSet<Integer>();
		
		// Get possible solutions and valid cell values
		for (List<Integer> possibleSolution : possibleSolutions) {
			if (isSolutionValid(possibleSolution)) {
				validGroupSolutions.add(possibleSolution);
				possibleCellValues.addAll(possibleSolution);
			}
		}
		
		// Update set of possible solutions for group
		possibleSolutions = validGroupSolutions;
		
		// Update possible cell values with results of possibleSolutions
		for (Cell cell : getCells()) {
			cell.removeAllValuesNotIn(possibleCellValues);
		}
		
		// get all unique possible orderings for all orderings
		List<Cell> cellList = getCellsAsList();
		
		Set<List<Integer>> possibleOrderings = getAllPossibleOrderings(cellList, possibleSolutions);
		
		for (int pos=0; pos<cellList.size(); pos++) {
			
			// TODO have some sort of null check here
			Integer valueAtPosInFirstOrdering = possibleOrderings.iterator().next().get(pos);
			boolean sameValAtPosForAllOrderings = true;
			
			for (List<Integer> possibleOrdering : possibleOrderings) {
				
				Integer valueAtPosInNthOrdering = possibleOrdering.get(pos);
				
				if (valueAtPosInFirstOrdering != valueAtPosInNthOrdering) {
					sameValAtPosForAllOrderings = false;
					break;
				}
			}
			
			// Same value in position x for all possible orderings, then assign that
			// cell that value as the cells solution
			if (sameValAtPosForAllOrderings) {
				cellList.get(pos).removeAllPossibleValuesExcept(valueAtPosInFirstOrdering);
			}
			
		}
			
		updatePossibleCellValuesUsingGuaranteedGroupSolutionValues(validGroupSolutions, p);
	}

	// TODO rename this method to something more suitable
	// TODO remove the puzzle argument
	private void updatePossibleCellValuesUsingGuaranteedGroupSolutionValues(
			Set<List<Integer>> validGroupSolutions, Puzzle p) {
		
		Set<Integer> guaranteedValuesForGroup = Utils.getIntersection(validGroupSolutions);
		
		for (Integer value : guaranteedValuesForGroup) {
			List<Cell> cellsInGroupWithValueAsPossibility = new ArrayList<Cell>();
			
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
					
					cellsInGroupWithValueAsPossibility.add(c);
				}
			}
			
			boolean atLeastOneCell = 1 <= cellsInGroupWithValueAsPossibility.size();
			boolean valueOnlyInOneRow = minRow==maxRow;
			boolean valueOnlyInOneCol = minCol==maxCol;
			
			if (valueOnlyInOneRow && atLeastOneCell) {
				// remove value as possibility from other cells in that row.
				Set<Cell> cellsInSameRow = cellsInGroupWithValueAsPossibility
						.get(0).getRowGroup().getCells();
				
				for (Cell c : cellsInSameRow) {
					if (!cellsInGroupWithValueAsPossibility.contains(c)) {
						c.removeValueFromPossible(value);
					}
				}
			}
			
			if (valueOnlyInOneCol && atLeastOneCell) {
				// remove value as possibility from other cells in that column.
				Set<Cell> cellsInSameCol = cellsInGroupWithValueAsPossibility
						.get(0).getColumnGroup().getCells();
				
				for (Cell c : cellsInSameCol) {
					if (!cellsInGroupWithValueAsPossibility.contains(c)) {
						c.removeValueFromPossible(value);
					}
				}
			}
		}
	}
	
}
