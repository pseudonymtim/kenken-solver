package com.kenkensolver.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class Cell {
	private static int ID_COUNTER = 100;
	
	private final Position position;
	private final Set<Integer> possibleValues;
	
	private int id;
	private BespokeGroup bespokeGroup;
	private Group rowGroup;
	private Group columnGroup;
	
	public Cell(Position pos, BespokeGroup bg, Group rowGroup2, Group columnGroup2) {
		id = ID_COUNTER++;
		position = pos;
		possibleValues = new HashSet<Integer>();
		bespokeGroup = bg;
		rowGroup = rowGroup2;
		columnGroup = columnGroup2;
	}
	
	public int getId() {
		return id;
	}
	
	public Position getPosition() {
		return position;
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

	public BespokeGroup getBespokeGroup() {
		return bespokeGroup;
	}

	public Group getRowGroup() {
		return rowGroup;
	}

	public Group getColumnGroup() {
		return columnGroup;
	}
	
	public void setBespokeGroup(BespokeGroup bespokeGroup) {
		this.bespokeGroup = bespokeGroup;
	}

	public void setRowGroup(Group rowGroup) {
		this.rowGroup = rowGroup;
	}

	public void setColumnGroup(Group columnGroup) {
		this.columnGroup = columnGroup;
	}

	public void removeValueFromPossible(int impossibleVal) {
		if (possibleValues.contains(impossibleVal)) {
			// Check if the last remaining possible value is being removed
			if (possibleValues.size() <= 1) {
				System.out.println("ERROR: Trying to remove the last possible value " + impossibleVal
						+ " for cell at position " + position.toString());
				throw new IllegalStateException();
			}
			
			possibleValues.remove(impossibleVal);
		}
	}

	public boolean isSolved() {
		return possibleValues.size() == 1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		StringJoiner sj = new StringJoiner(",");
		for (Integer possibleValue : possibleValues) {
			sj.add(possibleValue.toString());
		}
		
		sb.append("Cell=" + id);
		sb.append(" Pos=" + position);
		sb.append(" PossVals={" + sj.toString() + "}");
		
		return sb.toString();
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
			cellLine = " " + bespokeGroup.getResult() + 
					bespokeGroup.getOperation().getOperationSymbol() + cellLine;
		}
		else if (lineInCell == (cellHeight/2)+1) {
			String valueStr = "";
			List<Integer> possibleValuesList = new ArrayList<Integer>();
			possibleValuesList.addAll(possibleValues);
			Collections.sort(possibleValuesList);
			for (Integer value : possibleValuesList) {
				valueStr += value;
			}
			String whitespacePadding = cellLine.substring(
					0, Math.max(0, (cellWidth-valueStr.length())/2));
			cellLine = whitespacePadding + valueStr + cellLine;
		}
		
		return cellLine.substring(0, cellWidth);
	}

	public void removeAllValuesNotIn(Set<Integer> possibleValuesToKeep) {
		Set<Integer> posValsToRemove = new HashSet<Integer>();
		
		for (Integer posVal : possibleValues) {
			if (!possibleValuesToKeep.contains(posVal)) {
				posValsToRemove.add(posVal);
			}
		}
		
		possibleValues.removeAll(posValsToRemove);
	}

}
















