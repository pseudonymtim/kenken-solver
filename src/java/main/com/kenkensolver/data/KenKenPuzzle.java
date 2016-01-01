package com.kenkensolver.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KenKenPuzzle {
	public static final int CELL_WIDTH = 9;
	public static final int CELL_HEIGHT = 4;
	public static final char THICK_ROW_SEPERATOR = '#';
	public static final char THICK_COLUMN_SEPERATOR = '#';
	public static final char THICK_CORNER = '#';
	public static final char THIN_ROW_SEPERATOR = '-';
	public static final char THIN_COLUMN_SEPERATOR = '|';
	public static final char THIN_CORNER = '+';
	
	private Set<Group> groups;
	private Map<Position, Cell> positionCellMap;
	private int rows;
	private int cols;
	Cell[][] cellArray;
	
	private KenKenPuzzle() { }
	
	private KenKenPuzzle(int r, int c, Set<Group> g, Map<Position, Cell> posCellMap) {
		rows = r;
		cols = c;
		groups = g;
		positionCellMap = posCellMap;
		
		cellArray = new Cell[r][c];
		for (Position p : positionCellMap.keySet()) {
			cellArray[p.getRowIndex()][p.getColIndex()] = positionCellMap.get(p);
		}
	}
	
	public Set<Group> getGroups() {
		return groups;
	}

	public Map<Position, Cell> getPositionCellMap() {
		return positionCellMap;
	}
	
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public String toStringAsciiArt() {
		StringBuffer asciiArt = new StringBuffer();
		
		// Section of code that generates the top (and bottom thick lines
		asciiArt.append(
				genHorizontalEdge(CELL_WIDTH, THICK_ROW_SEPERATOR, THICK_CORNER) + "\n");
		
		// These loops do the rest of the work
		for (int currRow=0; currRow<rows; currRow++) {
			// this will iterate down one cell at a time
			
			for (int lineInCell=1; lineInCell<=CELL_HEIGHT; lineInCell++) {
				// this will iterate down one text line at a time (per verticle cell)
				
				asciiArt.append(THICK_COLUMN_SEPERATOR);
				
				for (int currCol=0; currCol<cols; currCol++) {
					// this will iterate through each cell in a row
					
					// get string for that cell at that height in the cell
					Cell currCell = cellArray[currRow][currCol];
					
					// Get string printout for this cell
					asciiArt.append(currCell.toAsciiString(lineInCell, CELL_WIDTH, CELL_HEIGHT));
					
					//Position to the right of current cell
					Position posRight = Position.getInstance(
							currCell.getPosition().getRowIndex(),
							currCell.getPosition().getColIndex() + 1);
					
					// check if next cell to the right is in the current group
					if (currCell.getGroup().getPositions().contains(posRight)) {
						asciiArt.append(THIN_COLUMN_SEPERATOR);
					}
					else {
						asciiArt.append(THICK_COLUMN_SEPERATOR);
					}
					
				}
				
				asciiArt.append("\n");
				
			}
			
			// generate the border between the 2 rows of cells
			asciiArt.append(THICK_COLUMN_SEPERATOR);
			
			for (int currCol=0; currCol<cols; currCol++) {
				
				Cell currCell = cellArray[currRow][currCol];
				Set<Position> currGroupPositions = currCell.getGroup().getPositions();
				
				Position posBelow = Position.getInstance(
						currCell.getPosition().getRowIndex() + 1,
						currCell.getPosition().getColIndex());
				
				for (int i=0; i<CELL_WIDTH; i++) {
					if (currGroupPositions.contains(posBelow)) {
						asciiArt.append(THIN_ROW_SEPERATOR);
					}
					else {
						asciiArt.append(THICK_ROW_SEPERATOR);
					}
					
				}
				
				// put char dividing cells horizontally here
				
				//Position to the right of current cell
				Position posToTheRight = Position.getInstance(
						currCell.getPosition().getRowIndex(), 
						currCell.getPosition().getColIndex() + 1);
				
				Position posToTheRightAndBelow = Position.getInstance(
						currCell.getPosition().getRowIndex() + 1, 
						currCell.getPosition().getColIndex() + 1);
				
				// check if next cell to the right is in the current group
				if (currGroupPositions.contains(posBelow)
						&& currGroupPositions.contains(posToTheRight)
						&& currGroupPositions.contains(posToTheRightAndBelow)) {
					asciiArt.append(THIN_CORNER);
				}
				else {
					asciiArt.append(THICK_CORNER);
				}
			}
			
			asciiArt.append("\n");
			
		}
		
		return asciiArt.toString();
	}

	private String genHorizontalEdge(int cellWidth, char thickHorizontalChar, char thickCornerChar) {
		StringBuffer sb = new StringBuffer();
		sb.append(thickCornerChar);
		for (int i=0; i<cols; i++) {
			for (int j=0; j<cellWidth; j++) {
				sb.append(thickHorizontalChar);
			}
			sb.append(thickCornerChar);
		}
		return sb.toString();
	}

	public static class Builder {
		private Set<Group> groups;
		private Map<Position, Cell> positionCellMap;
		private int rows;
		private int cols;
		
		public Builder() {
			groups = new HashSet<Group>();
			positionCellMap = new HashMap<Position, Cell>();
			rows = 0;
			cols = 0;
		}
		
		public Builder addGroup(int result, Operation operation, Position... positions) {
			Group newGroup = new Group.Builder()
					.result(result)
					.operation(operation)
					.positions(positions)
					.build();
			
			groups.add(newGroup);
			
			//figure out if size needs to be changed
			for (Position pos : positions) {
				
				// Update number of rows and columns
				rows = Math.max(rows, pos.getRowIndex() + 1);
				cols = Math.max(cols, pos.getColIndex() + 1);
				
				Cell newCell = null;
				
				if (positionCellMap.containsKey(pos)) {
					Cell oldCell = positionCellMap.get(pos);
					newCell = Cell.getInstance(pos, oldCell.getValue(), newGroup);
					
					Group oldGroup = oldCell.getGroup();
					
					groups.remove(oldGroup);
					if (oldGroup.getPositions().size() > 1) {
						// Add updated old group back in
						groups.add(new Group.Builder(oldGroup)
								.removePosition(pos)
								.build());
					}
				}
				else {
					newCell = Cell.getInstance(pos, newGroup);
				}
				
				if (newCell != null) {
					positionCellMap.put(pos, newCell);
				}
			}
			
			return this;
		}
		
		public KenKenPuzzle build() {
			KenKenPuzzle puzzle = null;
			if (positionCellMap.size() < rows*cols) {
				System.out.println("Not all positions have been assigned to a group");
			}
			else {
				puzzle = new KenKenPuzzle(rows, cols, groups, positionCellMap); 
			}
			return puzzle;
		}
	}
	
}
