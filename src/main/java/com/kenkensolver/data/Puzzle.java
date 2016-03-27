package com.kenkensolver.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
	private Set<Cage> bespokeGroups;
	private Map<Integer, Group> rowGroupMap;
	private Map<Integer, Group> columnGroupMap;
	private Map<Position, Cell> positionCellMap;
	private Cell[][] cellArray;
	
	private Puzzle() { }
	
	private Puzzle(int s, Set<Cage> bg, Map<Integer, Group> rgm, 
			Map<Integer, Group> cgm, Map<Position, Cell> pcm) {
		size = s;
		bespokeGroups = bg;
		rowGroupMap = rgm;
		columnGroupMap = cgm;
		positionCellMap = pcm;
		
		cellArray = new Cell[s][s];
		for (Position p : positionCellMap.keySet()) {
			cellArray[p.getRowIndex()][p.getColIndex()] = positionCellMap.get(p);
		}
	}
	
	// TODO: add some sort of boolean progress meter???
	
	public Set<Cage> getBespokeGroups() {
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
		Set<Cell> cells = new HashSet<>();
		cells.addAll(positionCellMap.values());
		return cells;
	}

	public Set<Cell> getAllCellsInSameRow(Cell cell) {
		int rowIndex = cell.getPosition().getRowIndex();
		return rowGroupMap.get(rowIndex).getCells();
	}
	
	public Set<Cell> getAllCellsInSameColumn(Cell cell) {
		int columnIndex = cell.getPosition().getColIndex();
		return columnGroupMap.get(columnIndex).getCells();
	}

	public boolean isSolved() {
		boolean isSolved = true;
		for (Cell c : getAllCells()) {
			isSolved &= c.isSolved();
		}
		return isSolved;
	}
	
	public String toStringDetailed() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("----------------------------------------------\n");
		sb.append("Puzzle Details\n");
		sb.append("\n");
		sb.append("Size: " + size + "\n");
		sb.append("\n");
		
		sb.append("Cells (" + positionCellMap.size() + "):" + "\n");
		List<Position> positions = new ArrayList<>();
		positions.addAll(positionCellMap.keySet());
		Collections.sort(positions);
		for (Position pos : positions) {
			Cell cell = positionCellMap.get(pos);
			sb.append("  " + cell + "\n");
//			sb.append("    " + cell.getRowGroup() + "\n"); // TODO remove these 2 lines
//			sb.append("    " + cell.getColumnGroup() + "\n");
		}
		sb.append("\n");
		
		sb.append("Bespoke Groups (" + bespokeGroups.size() + "):\n");
		for (Cage g : bespokeGroups) {
			sb.append("  " + g.toString() + "\n");
		}
		sb.append("\n");
		
		sb.append("Row Groups (" + rowGroupMap.size() + "):\n");
		for (int i=0; i<size; i++) {
			if (rowGroupMap.containsKey(i)) {
				sb.append("  Row:" + i + " " + rowGroupMap.get(i).toString() + "\n");
			}
		}
		sb.append("\n");
		
		sb.append("Column Groups (" + columnGroupMap.size() + "):\n");
		for (int i=0; i<size; i++) {
			if (columnGroupMap.containsKey(i)) {
				sb.append("  Column:" + i + " " + columnGroupMap.get(i).toString() + "\n");
			}
		}
		sb.append("\n");
		
		sb.append("----------------------------------------------\n");
		return sb.toString();
	}
	
	public String toStringAscii() {
		StringBuffer asciiStr= new StringBuffer();
		
		// Generates the top boarder line
		asciiStr.append(THICK_CORNER);
		for (int i=0; i<size; i++) {
			for (int j=0; j<CELL_WIDTH; j++) {
				asciiStr.append(THICK_CORNER);
			}
			asciiStr.append(THICK_CORNER);
		}
		asciiStr.append("\n");
		
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
					Position posRight = new Position(
							currCell.getPosition().getRowIndex(),
							currCell.getPosition().getColIndex() + 1);
					
					// check if next cell to the right is in the current group
					if (currCell.getCage().getPositions().contains(posRight)) {
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
				Set<Position> currGroupPositions = currCell.getCage().getPositions();
				
				Position posBelow = new Position(
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
				Position posToTheRight = new Position(
						currCell.getPosition().getRowIndex(), 
						currCell.getPosition().getColIndex() + 1);
				
				Position posToTheRightAndBelow = new Position(
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

	public Set<Group> getAllGroups() {
		Set<Group> groups = new HashSet<>();
		groups.addAll(bespokeGroups);
		groups.addAll(rowGroupMap.values());
		groups.addAll(columnGroupMap.values());
		return groups;
	}
	
	public static class Builder {
		private Set<Cage> cages = new HashSet<>();
		private Map<Integer, Group> rowGroupMap = new HashMap<>();
		private Map<Integer, Group> columnGroupMap = new HashMap<>();
		private Map<Position, Cell> positionCellMap = new HashMap<>();
		private int size = 0;
		
		public Builder() { }
		
		public Builder addCage(int result, Operation operation, Position... positions) {
			
			Cage newCage = new Cage(result, operation);
			cages.add(newCage);
			
			for (Position pos : positions) {
				
				// Update size if required
				size = Math.max(size, pos.getRowIndex() + 1);
				size = Math.max(size, pos.getColIndex() + 1);

				if (positionCellMap.containsKey(pos)) {
					// Position already specified, reassign cell to new group
					
					// Get the corresponding cell for that position
					Cell cell = positionCellMap.get(pos);
					cell.setCage(newCage);
					
					// Add existing cell to new bespoke group
					newCage.addCell(cell);
					
					// Remove this position from the cells current group 
					Cage oldCage = cell.getCage();
					oldCage.removeCell(cell);
				}
				else {
					// New position being specified
					Group rowGroup = getRowGroup(pos);
					Group columnGroup = getColumnGroup(pos);
					
					// Create the new cell
					Cell newCell = new Cell(pos, newCage, rowGroup, columnGroup);
					
					// Add cell to position cell map
					positionCellMap.put(pos, newCell);
					
					// Add cell to all appropriate groups
					newCage.addCell(newCell);
					rowGroup.addCell(newCell);
					columnGroup.addCell(newCell);
				}
				
			}
			
			return this;
		}

		private Group getColumnGroup(Position pos) {
			return getOrCreateGroup(columnGroupMap, pos.getColIndex());
		}

		private Group getRowGroup(Position pos) {
			return getOrCreateGroup(rowGroupMap, pos.getRowIndex());
		}
		
		private Group getOrCreateGroup(Map<Integer, Group> groupMap, Integer key) {
			Group g;
			if (groupMap.containsKey(key)) {
				g = groupMap.get(key);						
			}
			else {
				g = new Group();
				groupMap.put(key, g);
			}
			return g;
		}
		
		public Puzzle build() {
			// Remove all groups that do not have any positions assigned to them
			Set<Cage> groupsToRemove = new HashSet<>();
			for (Cage bg : cages) {
				if (bg.getPositions().isEmpty()) {
					groupsToRemove.add(bg);
				}
			}
			cages.removeAll(groupsToRemove);
			
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
				puzzle = new Puzzle(size, cages, rowGroupMap, columnGroupMap, positionCellMap); 
			}
			
			return puzzle;
		}
	}
	
}
