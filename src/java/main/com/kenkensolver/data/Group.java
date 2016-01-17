package com.kenkensolver.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.kenkensolver.solver.Utils;

public class Group {
	private final Set<Cell> cells;
	
	public Group() {
		cells = new HashSet<Cell>();
	}
	
	public Set<Cell> getCells() {
		return cells;
	}
	
	public Set<Position> getPositions() {
		Set<Position> positions = new HashSet<Position>();
		for (Cell c : cells) {
			positions.add(c.getPosition());
		}
		return positions;
	}
	
	public void removeCellAtPosition(Position p) {
		for (Cell c : cells) {
			if (c.getPosition().equals(p)) {
				cells.remove(c);
				break;
			}
		}
	}

	public void removeCell(Cell c) {
		cells.remove(c);
	}

	public void addCell(Cell c) {
		cells.add(c);
	}

	public boolean isSolved() {
		boolean isSolved = true;
		for (Cell cell : getCells()) {
			isSolved &= cell.isSolved();
		}
		return isSolved;
	}
	
	@Override
	public String toString() {
		StringJoiner cellsJoiner = new StringJoiner(";");
		for (Cell c : cells) {
			cellsJoiner.add(c.getId() + c.getPosition().toString());
		}
		
		return "Group={Cells={" + cellsJoiner.toString() + "}}";
	}

	public Position getMostTopLeftPosition() {
		Position mostTopLeft = null;
		
		for (Position p : getPositions()) {
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

	public Position getMostBottomRightPosition() {
		Position mostBottomRight = null;
		
		for (Position p : getPositions()) {
			if (mostBottomRight == null) {
				mostBottomRight = p;
			}
			else if (p.getRowIndex() > mostBottomRight.getRowIndex()) {
				mostBottomRight = p;
			}
			else if (p.getRowIndex() == mostBottomRight.getRowIndex()
					&& p.getColIndex() > mostBottomRight.getColIndex()) {
				mostBottomRight = p;
			}
		}
		
		return mostBottomRight;
	}
	
	protected List<Cell> getCellsAsList() {
		List<Cell> cellList = new ArrayList<Cell>();
		cellList.addAll(cells);
		return cellList;
	}
	
	protected boolean isSolutionValid(List<Integer> potentialSolution) {
		Set<List<Integer>> setOfPotentialSolution = new HashSet<List<Integer>>();
		setOfPotentialSolution.add(potentialSolution);
		
		Set<List<Integer>> possibleOrderings = getAllPossibleOrderings(
				getCellsAsList(), setOfPotentialSolution);
		return !possibleOrderings.isEmpty();
	}
	
	protected Set<List<Integer>> getAllPossibleOrderings(
			List<Cell> cellList, Set<List<Integer>> possibleSolutions) {
		
		// Returns empty set if the input conditions are wrong
		if (cellList == null || possibleSolutions == null
				|| cellList.isEmpty() || possibleSolutions.isEmpty()) {
			return new HashSet<List<Integer>>();
		}

		/*
		 * TODO Assuming that all possible solutions have the same number of items
		 * as the cell list does. 
		 */
		
		Set<List<Integer>> allOrderings = Utils.generateAllUniqueOrderings(possibleSolutions);
		Set<List<Integer>> possibleOrderings = new HashSet<List<Integer>>();
		
		// Loop through the orderings and test each one
		for (List<Integer> ordering : allOrderings) {
			
			// Check if the shape of the group supports the ordering
			if (isSolutionOrderValid(cellList, ordering)) {
				if (containsDuplicates(ordering)) {
					if (isSolutionShapeValid(cellList, ordering)) {
						possibleOrderings.add(ordering);
					}
				}
				else {
					possibleOrderings.add(ordering);
				}
			}
			
		}
		
		return possibleOrderings;
	}

	private boolean isSolutionOrderValid(List<Cell> cellList, List<Integer> ordering) {
		if (cellList.size() != ordering.size()) {
			return false;
		}
		
		// Check if this ordering is possible given possible cell values
		for (int i=0; i<ordering.size(); i++) {
			Integer currValue = ordering.get(i);
			Cell currCell = cellList.get(i);
			
			// Check if the possible cell values line up with what's assigned
			if (!currCell.getPossibleValues().contains(currValue)) {
				return false;
			}
		}
		return true;
	}

	private boolean isSolutionShapeValid(List<Cell> cellList, List<Integer> ordering) {
		// Get group row and column limits
		Position mostTopLeft = getMostTopLeftPosition();
		Position mostBottomRight = getMostBottomRightPosition();
		
		int minRow = mostTopLeft.getRowIndex();
		int maxRow = mostBottomRight.getRowIndex();
		int minCol = mostTopLeft.getColIndex();
		int maxCol = mostBottomRight.getColIndex();
		
		boolean groupSpansMultipleRows = minRow!=maxRow;
		boolean groupSpansMultipleCols = minCol!=maxCol;
		
		boolean okForRows = true;
		
		if (groupSpansMultipleCols) {
			// Then you need to check each ROW of the proposed solution for uniqueness
			
			for (int currRow=minRow; currRow<=maxRow; currRow++) {
				
				List<Integer> list = new ArrayList<Integer>();
				Set<Integer> set = new HashSet<Integer>();
				
				// Get the proposed values for the current row
				for (int i=0; i<cellList.size(); i++) {
					Cell currCell = cellList.get(i);
					if (currRow == currCell.getPosition().getRowIndex()) {
						// Cell is in the current row, add it's proposed value
						list.add(ordering.get(i));
						set.add(ordering.get(i));
					}
				}
				
				// Check if the proposed values for that row are unique
				if (list.size() != set.size()) {
					okForRows = false;
					break;
				}
			}
		}
		
		boolean okForCols = true;
		
		if (okForRows && groupSpansMultipleRows) {
			// Then you need to check each COLUMN of the proposed solution for uniqueness
			
			for (int currCol=minCol; currCol<=maxCol; currCol++) {
				
				List<Integer> list = new ArrayList<Integer>();
				Set<Integer> set = new HashSet<Integer>();
				
				// Get the proposed values for the current row
				for (int i=0; i<cellList.size(); i++) {
					Cell currCell = cellList.get(i);
					if (currCol == currCell.getPosition().getColIndex()) {
						// Cell is in the current row, add it's proposed value
						list.add(ordering.get(i));
						set.add(ordering.get(i));
					}
				}
				
				// Check if the proposed values for that row are unique
				if (list.size() != set.size()) {
					okForCols = false;
					break;
				}
			}
		}
		
		return okForRows && okForCols;
	}

	private boolean containsDuplicates(List<Integer> list) {
		// This works because java Sets do not allow duplicates
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(list);
		return set.size() != list.size();
	}

	public void refineSolutionSpace(Puzzle p) {
		
		// Get set of unique values for the group
		Set<Integer> allValues = new HashSet<Integer>();
		for (Cell c : cells) {
			allValues.addAll(c.getPossibleValues());
		}
		
		// Check all values for this group
		for (Integer value : allValues) {
			Set<Cell> cellsThatCanHaveThatValue = new HashSet<Cell>();
			
			// Get cells that could be that value 
			for (Cell c : cells) {
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
			 * puzzle for groups of possibilities that may rule out possbilities in other 
			 * cells.
			 */
			
		}
		
	}
	
}






