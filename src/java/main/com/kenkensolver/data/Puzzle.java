package com.kenkensolver.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Puzzle {
	private static final int CELL_WIDTH = 9;
	private static final int CELL_HEIGHT = 4;
	private static final char THICK_ROW_SEPERATOR = '#';
	private static final char THICK_COLUMN_SEPERATOR = '#';
	private static final char THICK_CORNER = '#';
	private static final char THIN_ROW_SEPERATOR = '-';
	private static final char THIN_COLUMN_SEPERATOR = '|';
	private static final char THIN_CORNER = '+';
	
	private int size;
	private Set<Group> bespokeGroups;
	private Map<Integer, Group> rowGroupMap;
	private Map<Integer, Group> columnGroupMap;
	private Map<Position, Cell> positionCellMap;
	
	private Puzzle() { }
	
	private Puzzle(int s, Set<Group> bg, Map<Integer, Group> rgm, 
			Map<Integer, Group> cgm, Map<Position, Cell> pcm) {
		size = s;
		bespokeGroups = bg;
		rowGroupMap = rgm;
		columnGroupMap = cgm;
		positionCellMap = pcm;
	}
	
	public Set<Group> getBespokeGroups() {
		return bespokeGroups;
	}

	public Map<Integer, Group> getRowGroupMap() {
		return rowGroupMap;
	}

	public Map<Integer, Group> getColumnGroupMap() {
		return columnGroupMap;
	}

	public int getSize() {
		return size;
	}

	public Map<Position, Cell> getPositionCellMap() {
		return positionCellMap;
	}
	
	public Set<Cell> getAllCells() {
		Set<Cell> cells = new HashSet<Cell>();
		cells.addAll(positionCellMap.values());
		return cells;
	}

	public Set<Cell> getAllCellsInSameRow(Cell cell) {
		int rowIndex = cell.getPosition().getRowIndex();
		Group rowGroup = rowGroupMap.get(rowIndex);
		Set<Position> rowPositions = rowGroup.getPositions();
		Set<Cell> rowCells = new HashSet<Cell>();
		for (Position p : rowPositions) {
			rowCells.add(positionCellMap.get(p));
		}
		return rowCells;
	}

	public Set<Cell> getAllCellsInSameColumn(Cell cell) {
		int colIndex = cell.getPosition().getColIndex();
		Group colGroup = columnGroupMap.get(colIndex);
		Set<Position> colPositions = colGroup.getPositions();
		Set<Cell> colCells = new HashSet<Cell>();
		for (Position p : colPositions) {
			colCells.add(positionCellMap.get(p));
		}
		return colCells;
	}
	
	public String toStringAscii() {
		StringBuffer asciiStr= new StringBuffer();
		
		// Section of code that generates the top boarder line
		asciiStr.append(THICK_CORNER);
		for (int i=0; i<size; i++) {
			for (int j=0; j<CELL_WIDTH; j++) {
				asciiStr.append(THICK_CORNER);
			}
			asciiStr.append(THICK_CORNER);
		}
		asciiStr.append("\n");
		
		Cell[][] cellArray = new Cell[size][size];
		for (Position p : positionCellMap.keySet()) {
			cellArray[p.getRowIndex()][p.getColIndex()] = positionCellMap.get(p);
		}
		
		// These loops do the rest of the work
		for (int currRow=0; currRow<size; currRow++) {
			// this will iterate down one cell at a time
			
			for (int lineInCell=1; lineInCell<=CELL_HEIGHT; lineInCell++) {
				// this will iterate down one text line at a time (per verticle cell)
				
				asciiStr.append(THICK_COLUMN_SEPERATOR);
				
				for (int currCol=0; currCol<size; currCol++) {
					// this will iterate through each cell in a row
					
					// get string for that cell at that height in the cell
					Cell currCell = cellArray[currRow][currCol];
					
					// Get string printout for this cell
					asciiStr.append(currCell.toStringAscii(lineInCell, CELL_WIDTH, CELL_HEIGHT));
					
					//Position to the right of current cell
					Position posRight = Position.getInstance(
							currCell.getPosition().getRowIndex(),
							currCell.getPosition().getColIndex() + 1);
					
					// check if next cell to the right is in the current group
					if (currCell.getBespokeGroup().getPositions().contains(posRight)) {
						asciiStr.append(THIN_COLUMN_SEPERATOR);
					}
					else {
						asciiStr.append(THICK_COLUMN_SEPERATOR);
					}
					
				}
				
				asciiStr.append("\n");
				
			}
			
			// generate the border between the 2 rows of cells
			asciiStr.append(THICK_COLUMN_SEPERATOR);
			
			for (int currCol=0; currCol<size; currCol++) {
				
				Cell currCell = cellArray[currRow][currCol];
				Set<Position> currGroupPositions = currCell.getBespokeGroup().getPositions();
				
				Position posBelow = Position.getInstance(
						currCell.getPosition().getRowIndex() + 1,
						currCell.getPosition().getColIndex());
				
				for (int i=0; i<CELL_WIDTH; i++) {
					if (currGroupPositions.contains(posBelow)) {
						asciiStr.append(THIN_ROW_SEPERATOR);
					}
					else {
						asciiStr.append(THICK_ROW_SEPERATOR);
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
					asciiStr.append(THIN_CORNER);
				}
				else {
					asciiStr.append(THICK_CORNER);
				}
			}
			
			asciiStr.append("\n");
			
		}
		
		return asciiStr.toString();
	}

	public static class Builder {
		private Set<Group> bespokeGroups;
		private Map<Integer, Group> rowGroupMap;
		private Map<Integer, Group> columnGroupMap;
		private Map<Position, Cell> positionCellMap;
		private int size;
		
		public Builder() {
			bespokeGroups = new HashSet<Group>();
			rowGroupMap = new HashMap<Integer, Group>();
			columnGroupMap = new HashMap<Integer, Group>();
			positionCellMap = new HashMap<Position, Cell>();
			size = 0;
		}
		
		public Builder addGroup(int result, Operation operation, Position... positions) {
			Group newBespokeGroup = new Group(result, operation, positions);
			bespokeGroups.add(newBespokeGroup);
			
			for (Position pos : positions) {
				
				// Update size if required
				size = Math.max(size, pos.getRowIndex() + 1);
				size = Math.max(size, pos.getColIndex() + 1);

				// Figure out if position has already been assigned to a group
				if (positionCellMap.containsKey(pos)) {
					// Position already specified, reassign cell to new group
					Cell cell = positionCellMap.get(pos);
					
					// Remove this position from the cells current group 
					Group oldBespokeGroup = cell.getBespokeGroup();
					oldBespokeGroup.removePosition(pos);
					oldBespokeGroup.removeCell(cell);
					
					// Assign cell to have new bespoke group
					cell.setBespokeGroup(newBespokeGroup);
					newBespokeGroup.addCell(cell);
				}
				else {
					// New position being specified
					
					// Get the row group
					Group rowGroup = null;
					if (rowGroupMap.containsKey(pos.getRowIndex())) {
						rowGroup = rowGroupMap.get(pos.getRowIndex());						
					}
					else {
						rowGroup = new Group(0, Operation.UNIQUE, pos);
						rowGroupMap.put(pos.getRowIndex(), rowGroup);
					}
					
					// Get the column group
					Group columnGroup = null;
					if (columnGroupMap.containsKey(pos.getColIndex())) {
						columnGroup = columnGroupMap.get(pos.getColIndex());						
					}
					else {
						columnGroup = new Group(0, Operation.UNIQUE, pos);
						columnGroupMap.put(pos.getColIndex(), rowGroup);
					}
					
					// Create the new cell
					Cell newCell = new Cell(pos, newBespokeGroup, rowGroup, columnGroup);
					positionCellMap.put(pos, newCell);
					newBespokeGroup.addCell(newCell);
				}
				
			}
			
			return this;
		}
		
		public Puzzle build() {
			// Remove all groups that do not have any positions assigned to them
			Set<Group> groupsToRemove = new HashSet<Group>();
			for (Group bg : bespokeGroups) {
				if (bg.getPositions().isEmpty()) {
					groupsToRemove.add(bg);
				}
			}
			bespokeGroups.removeAll(groupsToRemove);
			
			// TODO: There should be more validation of the puzzle here, not sure what though
			
			// Validation: All bespoke groups with NONE operation only have one position
			// Validation: All row and col groups have 'size' number of positions
			
			Puzzle puzzle = null;
			
			if (positionCellMap.size() != size*size) {
				// As size is determined off the most remote position, there can
				// never be more positions than the size calculation.
				System.out.println("Not all positions have been assigned to a group");
			}
			else {
				puzzle = new Puzzle(size, bespokeGroups, rowGroupMap, 
						columnGroupMap, positionCellMap); 
			}
			
			return puzzle;
		}
	}
	
}
