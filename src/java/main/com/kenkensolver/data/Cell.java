package com.kenkensolver.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {
	private static final int NO_VALUE = -1;
	private final Position position;
	private final Set<Integer> possibleValues;
	
	private int value;
	private Group bespokeGroup;
	private Group rowGroup;
	private Group columnGroup;
	
	public Cell(Position pos, Group bg, Group rg, Group cg) {
		position = pos;
		value = NO_VALUE;
		possibleValues = new HashSet<Integer>();
		bespokeGroup = bg;
		rowGroup = rg;
		columnGroup = cg;
	}
	
	public Position getPosition() {
		return position;
	}

	public int getValue() {
		return value;
	}

	public Set<Integer> getPossibleValues() {
		return possibleValues;
	}

	public Set<List<Integer>> getPossibleValuesAsSetOfLists() {
		Set<List<Integer>> posVals = new HashSet<List<Integer>>();
		for (Integer possibleValue : possibleValues) {
			List<Integer> newList = new ArrayList<Integer>();
			newList.add(possibleValue);
			posVals.add(newList);
		}
		return posVals;
	}

	public Group getBespokeGroup() {
		return bespokeGroup;
	}

	public Group getRowGroup() {
		return rowGroup;
	}

	public Group getColumnGroup() {
		return columnGroup;
	}
	
	public void setBespokeGroup(Group bespokeGroup) {
		this.bespokeGroup = bespokeGroup;
	}

	public void setRowGroup(Group rowGroup) {
		this.rowGroup = rowGroup;
	}

	public void setColumnGroup(Group columnGroup) {
		this.columnGroup = columnGroup;
	}

	public void removeValueFromPossible(int imposVal) {
		if (possibleValues.contains(imposVal)) {
			// Check if the last remaining possible value is being removed
			if (possibleValues.size() <= 1) {
				System.out.println("ERROR: Trying to remove the last possible value " + imposVal
						+ " for cell at position " + position.toString());
				throw new IllegalStateException();
			}
			
			possibleValues.remove(imposVal);
			
			// If there's only one value left then assign it as the cell's value
			if (possibleValues.size() == 1 && value == NO_VALUE) {
				for (Integer i : possibleValues) {
					value = i;
				}
			}
		}
	}
	
	public boolean assignCellValue() {
		boolean change = false;
		if (possibleValues.size() == 1 && value == NO_VALUE) {
			change = true;
			for (Integer cellVal : possibleValues) {
				value = cellVal.intValue();
			}
		}
		return change;
	}

	public boolean isSolved() {
		return value != NO_VALUE;
	}

	/**
	 * Function to output the puzzle as ascii art
	 * 
	 * @param lineInCell Line of the cell currently being printed. 1<=lineInCell<=cellHeight
	 * @param cellWidth Width of the cell
	 * @param cellHeight Height of the cell
	 * @return String containing the contents of the cell on that line
	 */
	public String toStringAscii(int lineInCell, int cellWidth, int cellHeight) {
		String cellLine = "                           ";
		
		// is string most top, and if tie, most left?
		if (lineInCell == 1 && position.equals(bespokeGroup.getMostTopLeftPosition())) {
			cellLine = " " + bespokeGroup.getResult() + bespokeGroup.getOperation() + cellLine;
		}
		else if (value > 0 && lineInCell == (cellHeight/2)+1) {
			String valueStr = String.valueOf(value);
			String whitespacePadding = cellLine.substring(
					0, Math.max(0, (cellWidth-valueStr.length())/2));
			cellLine = whitespacePadding + valueStr + cellLine;
		}
		
		return cellLine.substring(0, cellWidth);
	}

}
