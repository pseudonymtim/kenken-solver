package com.kenkensolver.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kenkensolver.data.BespokeGroup;
import com.kenkensolver.data.Cell;
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
		
		int puzzlePass=1;
		
		while (!p.isSolved()) {
			
			refineGroupSolutions(p);
			
			for (Cell c : p.getAllCells()) {
				c.updateCellsInSameRowAndColumn();
			}
			
			int cellsSolved = 0;
			int possibleCellValues = 0;
			for (Cell c : p.getAllCells()) {
				if (c.isSolved()) {
					cellsSolved++;
				}
				possibleCellValues += c.getPossibleValues().size();
			}
			
			int possibleGroupSolutions = 0;
			for (BespokeGroup bg : p.getBespokeGroups()) {
				possibleGroupSolutions += bg.getPossibleSolutions().size();
			}
			
			System.out.println(p.toStringDetailed());
			System.out.println(p.toStringAscii());
			System.out.println("puzzlePass=" + puzzlePass++
					+ " cellsSolved=" + cellsSolved
					+ " possibleCellValues=" + possibleCellValues
					+ " possibleGroupSolutions=" + possibleGroupSolutions);
			
		}
		
	}
	
	private void refineGroupSolutions(Puzzle p) {
		
		for (Group group : p.getAllGroups()) {
			if (!group.isSolved()) {
				if (group instanceof BespokeGroup) {
					refineBespokeGroupSolutions((BespokeGroup)group, p);
				}
				else {
					refineRowColumnGroupSolutions(group);
				}
			}
		}
		
	}

	private void refineBespokeGroupSolutions(BespokeGroup group, Puzzle p) {

		refinePossibleSolutions(group);
		
		updatePossCellValsBasedOnPossGroupSolnOrderings(group);
			
		updatePossibleCellValuesUsingGuaranteedGroupSolutionValues(group, p);
		
	}

	private void refinePossibleSolutions(BespokeGroup group) {
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

	private void updatePossCellValsBasedOnPossGroupSolnOrderings(BespokeGroup group) {
		// get all unique possible orderings for all orderings
		List<Cell> cellList = group.getCellsAsList();
		
		Set<List<Integer>> allOrderings = Utils.generateAllUniqueOrderings(
				group.getPossibleSolutions());
		
		Set<List<Integer>> possibleOrderings = new HashSet<List<Integer>>();
		
		for (List<Integer> ordering : allOrderings) {
			if (group.isSolutionOrderValid(cellList, ordering)) {
				possibleOrderings.add(ordering);
			}
		}
		
		for (int pos=0; pos<cellList.size(); pos++) {
			
			// TODO have some sort of null check here
			Set<Integer> possibleValuesForNthCell = new HashSet<Integer>();
			
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
			BespokeGroup bg, Puzzle p) {
		
		Set<Integer> guaranteedValuesForGroup = Utils.getIntersection(bg.getPossibleSolutions());
		
		for (Integer value : guaranteedValuesForGroup) {
			List<Cell> cellsInGroupWithValueAsPossibility = new ArrayList<Cell>();
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void refineRowColumnGroupSolutions(Group group) {
		
		// Get set of unique values for the group
		Set<Integer> allValues = new HashSet<Integer>();
		for (Cell c : group.getCells()) {
			allValues.addAll(c.getPossibleValues());
		}
		
		// Check all values for this group
		for (Integer value : allValues) {
			Set<Cell> cellsThatCanHaveThatValue = new HashSet<Cell>();
			
			// Get cells that could be that value 
			for (Cell c : group.getCells()) {
				if (c.getPossibleValues().contains(value)) {
					cellsThatCanHaveThatValue.add(c);
				}
			}
			
			// If only one cell can be that value, make that value the only possible one
			if (cellsThatCanHaveThatValue.size() == 1) {
				Cell c = cellsThatCanHaveThatValue.iterator().next();
				c.removeAllPossibleValuesExcept(value);
			}
			/*
			 * TODO this last if-then is a problem because it doesn't look across the whole
			 * puzzle for groups of possibilities that may rule out possibilities in other 
			 * cells.
			 */
			
		}
		
	}

	private void hydrateAllCellsWithPossibleValues(Collection<Cell> cells, int size) {
		Set<Integer> possibleCellValues = new HashSet<Integer>();
		
		for (int i=1; i<=size; i++) {
			possibleCellValues.add(i);
		}
		
		for (Cell c : cells) {
			c.getPossibleValues().addAll(possibleCellValues);
		}
	}

	private void processGroupsGenSolutions(Set<BespokeGroup> groups) {
		// Go through each group and figure out solutions
		for (BespokeGroup group : groups) {
			
			Set<List<Integer>> possibleSolutions = new HashSet<List<Integer>>();
			
			for (Cell c : group.getCells()) {
				possibleSolutions = Utils.crossProduct(
						possibleSolutions,
						c.getPossibleValuesAsSetOfLists()); 
			}
			
			possibleSolutions = Utils.removeDuplicateListsIgnoreOrdering(possibleSolutions);
			
			group.setPossibleSolutions(possibleSolutions);

			// TODO: only keep solutions that are valid??
			
		}
	}

	
	
	/*
	 * TODO: Read this...
	 * 
	 * GOT IT
	 * 
	 * Find possible solution(s) for a group
	 * iterate through all cells and update based on those possibilities.
	 */
	
	/*
	 * Start with puzzle
	 * Iterate through cells
	 * - populate with possible values
	 * 
	 */
	
	/*
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
