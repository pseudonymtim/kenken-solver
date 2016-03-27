package com.kenkensolver.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kenkensolver.data.Cage;
import com.kenkensolver.data.Cell;
import com.kenkensolver.data.CellUtils;
import com.kenkensolver.data.Group;
import com.kenkensolver.data.Puzzle;

public class BasicSolver implements Solver {

	@Override
	public void solve(Puzzle p) {
		
		// Hydrate cells with possible values
		hydrateAllCellsWithPossibleValues(p.getAllCells(), p.getSize());
		
		// TODO: add step to check for cells that only have one value
		
		// process the groups
		processGroupsGenSolutions(p.getBespokeGroups());
		
		int puzzlePass = 1;
		int cellsSolved = 0;
		int possibleCellValues = 0;
		int possibleGroupSolutions = 0;
		
		boolean progressMade = true;
		
		while (!p.isSolved() && progressMade) {
			
			refineGroupSolutions(p);
			
			for (Cell c : p.getAllCells()) {
				c.updateCellsInSameRowAndColumn();
			}
			
			int cellsSolvedThisIteration = 0;
			int possibleCellValuesThisIteration = 0;
			for (Cell c : p.getAllCells()) {
				if (c.isSolved()) {
					cellsSolvedThisIteration++;
				}
				possibleCellValuesThisIteration += c.getPossibleValues().size();
			}
			
			int possibleGroupSolutionsThisIteration = 0;
			for (Cage bg : p.getBespokeGroups()) {
				possibleGroupSolutionsThisIteration += bg.getPossibleSolutions().size();
			}
			
			if (cellsSolved == cellsSolvedThisIteration 
					&& possibleCellValues == possibleCellValuesThisIteration
					&& possibleGroupSolutions == possibleGroupSolutionsThisIteration) {
				progressMade = false;
			}
			else {
				cellsSolved = cellsSolvedThisIteration;
				possibleCellValues = possibleCellValuesThisIteration;
				possibleGroupSolutions = possibleGroupSolutionsThisIteration;
			}
			
//			System.out.println(p.toStringDetailed());
//			System.out.println(p.toStringAscii());
//			System.out.println("puzzlePass=" + puzzlePass++
//					+ " cellsSolved=" + cellsSolved
//					+ " possibleCellValues=" + possibleCellValues
//					+ " possibleGroupSolutions=" + possibleGroupSolutions);
		}
		
	}
	
	private void refineGroupSolutions(Puzzle p) {
		
		for (Group group : p.getAllGroups()) {
			if (!group.isSolved()) {
				if (group instanceof Cage) {
					refineBespokeGroupSolutions((Cage)group, p);
				}
				else {
					refineRowColumnGroupSolutions(group);
				}
			}
		}
		
	}

	private void refineBespokeGroupSolutions(Cage group, Puzzle p) {

		refinePossibleSolutions(group);
		
		updatePossCellValsBasedOnPossGroupSolnOrderings(group);
			
		updatePossibleCellValuesUsingGuaranteedGroupSolutionValues(group, p);
		
	}

	private void refinePossibleSolutions(Cage group) {
		Set<List<Integer>> validGroupSolutions = new HashSet<List<Integer>>();
		
		// Get possible solutions and valid cell values
		for (List<Integer> possibleSolution : group.getPossibleSolutions()) {
			if (group.isSolutionValid(possibleSolution)) {
				validGroupSolutions.add(possibleSolution);
			}
		}
		
		// Update set of possible solutions for group
		group.setPossibleSolutions(validGroupSolutions);
	}

	private void updatePossCellValsBasedOnPossGroupSolnOrderings(Cage group) {
		// get all unique possible orderings for all orderings
		List<Cell> cellList = group.getCellsAsList();
		
		Set<List<Integer>> allOrderings = Utils.generateAllUniqueOrderings(
				group.getPossibleSolutions());
		
		Set<List<Integer>> possibleOrderings = new HashSet<>();
		
		for (List<Integer> ordering : allOrderings) {
			if (CellUtils.isSolutionOrderValid(cellList, ordering)) {
				possibleOrderings.add(ordering);
			}
		}
		
		for (int pos=0; pos<cellList.size(); pos++) {
			
			// TODO have some sort of null check here
			Set<Integer> possibleValuesForNthCell = new HashSet<>();
			
			// Get set of all numbers that appear in that position across all possible orderings
			for (List<Integer> possibleOrdering : possibleOrderings) {
				possibleValuesForNthCell.add(possibleOrdering.get(pos));
			}
			
			// Assign those possibilities to the possible values for the cell
			cellList.get(pos).removeAllValuesNotIn(possibleValuesForNthCell);
		}
	}
	
	// TODO rename this method to something more suitable
	// TODO remove the puzzle argument
	private void updatePossibleCellValuesUsingGuaranteedGroupSolutionValues(
			Cage bg, Puzzle p) {
		
		Set<Integer> guaranteedValuesForGroup = Utils.getIntersection(bg.getPossibleSolutions());
		
		for (Integer value : guaranteedValuesForGroup) {
			List<Cell> cellsInGroupWithValueAsPossibility = new ArrayList<>();
			
			int minRow = -1;
			int maxRow = -1;
			int minCol = -1;
			int maxCol = -1;
			
			for (Cell c : bg.getCells()) {
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
						c.removePossibleValue(value);
					}
				}
			}
			
			if (valueOnlyInOneCol && atLeastOneCell) {
				// remove value as possibility from other cells in that column.
				Set<Cell> cellsInSameCol = cellsInGroupWithValueAsPossibility
						.get(0).getColumnGroup().getCells();
				
				for (Cell c : cellsInSameCol) {
					if (!cellsInGroupWithValueAsPossibility.contains(c)) {
						c.removePossibleValue(value);
					}
				}
			}
		}
	}

	private void refineRowColumnGroupSolutions(Group group) {
		
		// Get set of unique values for the group
		Set<Integer> allValues = new HashSet<>();
		for (Cell c : group.getCells()) {
			allValues.addAll(c.getPossibleValues());
		}
		
		// Check all values for this group
		for (Integer value : allValues) {
			Set<Cell> cellsThatCanHaveThatValue = new HashSet<>();
			
			// Get cells that could be that value 
			for (Cell c : group.getCells()) {
				if (c.getPossibleValues().contains(value)) {
					cellsThatCanHaveThatValue.add(c);
				}
			}
			
			// If only one cell can be that value, make that value the only possible one
			if (cellsThatCanHaveThatValue.size() == 1) {
				Cell c = cellsThatCanHaveThatValue.iterator().next();
				c.removePossibleValuesExcept(value);
			}
			/*
			 * TODO this last if-then is a problem because it doesn't look across the whole
			 * puzzle for groups of possibilities that may rule out possibilities in other 
			 * cells.
			 */
			
		}
		
	}

	private void hydrateAllCellsWithPossibleValues(Collection<Cell> cells, int size) {
		Set<Integer> possibleCellValues = new HashSet<>();
		
		for (int i=1; i<=size; i++) {
			possibleCellValues.add(i);
		}
		
		for (Cell c : cells) {
			c.getPossibleValues().addAll(possibleCellValues);
		}
	}

	private void processGroupsGenSolutions(Set<Cage> cages) {
		// Go through each group and figure out solutions
		for (Cage cage : cages) {
			Set<Integer> possibleSolutionValues = new HashSet<>();
			
			for (Cell c : cage.getCells()) {
				possibleSolutionValues.addAll(c.getPossibleValues());
			}
			
			int rows = cage.getRowSpan();
			int columns = cage.getColumnSpan();
			int allowedRepeats = Math.min(rows, columns) - 1;
			
			List<Integer> allowedValues = new ArrayList<>();
			for (int i=0; i<=allowedRepeats; i++) {
				allowedValues.addAll(possibleSolutionValues);
			}
			
			int numberOfCells = cage.getCells().size();
			cage.setPossibleSolutions(generatePossibleSolutions(allowedValues, numberOfCells));
		}
	}

	protected Set<List<Integer>> generatePossibleSolutions(
			List<Integer> allowedValues, int solutionSize) 
			throws IllegalArgumentException {
		
		if (solutionSize < 1 || solutionSize > allowedValues.size()) {
			throw new IllegalArgumentException("Argument number 2 must have value greater than "
					+ "zero and less than the number of allow values, value passed was "
					+ "[" + solutionSize + "].");
		}
		
		if (allowedValues == null || allowedValues.isEmpty()) {
			throw new IllegalArgumentException("Argument number 1 must not be null or empty.");
		}
		
		Collections.sort(allowedValues);
		
		Set<List<Integer>> possibleSolutions = new HashSet<>();
		
		if (solutionSize == 1) {
			for (Integer possibleValue : allowedValues) {
				List<Integer> possibleSolution = new ArrayList<>();
				possibleSolution.add(possibleValue);
				possibleSolutions.add(possibleSolution);
			}
		}
		else {
			Integer currVal = null;
			for (int i=0; i<allowedValues.size()-solutionSize+1; i++) {
				
				// If the next number was the same as the last number, skip it
				if (allowedValues.get(i).equals(currVal)) {
					continue;
				}
				else {
					currVal = allowedValues.get(i);
				}
				
				List<Integer> reducedList = allowedValues.subList(i+1, allowedValues.size());
				Set<List<Integer>> solutions = generatePossibleSolutions(reducedList, solutionSize-1);
				
				for (List<Integer> solution : solutions) {
					solution.add(0, currVal);
				}
				
				possibleSolutions.addAll(solutions);
			}
		}
		
		return possibleSolutions;
	}
	
	
	/*
	 * TODO: Read this...
	 * 
	 * The base case to stop repeating the processing is when all the cells have only 
	 * one possible value left.
	 * 
	 * Per bespoke group
	 * - Generate all possible solutions
	 *     - Solutions have same length as number of positions in group
	 *     - Solutions have uniqueness limit based on how many rows/columns the group covers
	 * 
	 * Take group
	 * Get positions
	 * Get cells
	 * Take cross product of the possible values for each cell
	 * That will generate all possible solutions for that group
	 * - test solutions against uniqueness constraints
	 * - test solutions against operation and result
	 * - keep only those that pass both tests
	 * 
	 */

}
