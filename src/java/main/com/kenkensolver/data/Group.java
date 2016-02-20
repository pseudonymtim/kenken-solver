package com.kenkensolver.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.kenkensolver.solver.Utils;

public class Group {
	
	private final Set<Cell> cells = new HashSet<>();
	
	public Group() { }
	
	public Set<Cell> getCells() {
		return cells;
	}
	
	public List<Cell> getCellsAsList() {
		List<Cell> cellList = new ArrayList<>();
		cellList.addAll(cells);
		return cellList;
	}
	
	public Set<Position> getPositions() {
		Set<Position> positions = new HashSet<>();
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

	public void addCells(Collection<Cell> cs) {
		cells.addAll(cs);
	}

	public boolean isSolved() {
		for (Cell cell : getCells()) {
			if (!cell.isSolved()) {
				return false;
			}
		}
		return true;
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
	
	@Override
	public String toString() {
		StringJoiner cellsJoiner = new StringJoiner(";");
		for (Cell c : cells) {
			cellsJoiner.add(c.getId() + c.getPosition().toString());
		}
		
		return "Group={Cells={" + cellsJoiner.toString() + "}}";
	}
	
	public boolean isSolutionValid(List<Integer> potentialSolution) {
		Set<List<Integer>> possibleSolutions = new HashSet<>();
		possibleSolutions.add(potentialSolution);
	
		List<Cell> cellList = getCellsAsList();
		Set<List<Integer>> allOrderings = Utils.generateAllUniqueOrderings(possibleSolutions);
		
		/*
		 * As soon as you find a possible ordering, return true. 
		 */
		
		// Loop through the orderings and test each one
		for (List<Integer> ordering : allOrderings) {
			
			// Check if the shape of the group supports the ordering
			if (CellUtils.isSolutionOrderValid(cellList, ordering)) {
				if (containsDuplicates(ordering)) {
					if (isSolutionShapeValid(cellList, ordering)) {
						return true;
					}
				}
				else {
					return true;
				}
			}
			
		}
		
		return false;
	}

	protected boolean containsDuplicates(List<Integer> list) {
		// This works because java Sets do not allow duplicates
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(list);
		return set.size() != list.size();
	}
	
	protected boolean isSolutionShapeValid(List<Cell> cellList, List<Integer> ordering) {
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
	
}






