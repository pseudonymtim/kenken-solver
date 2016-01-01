package com.kenkensolver.data;

public class Cell {
	private final Position position;
	private final int value;
	private final Group group;
	
	private Cell(Position p, int v, Group g) {
		position = p;
		value = v;
		group = g;
	}
	
	public Position getPosition() {
		return position;
	}

	public int getValue() {
		return value;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public static Cell getInstance(Position pos, int value, Group group) {
		return new Cell(pos, value, group);
	}
	
	public static Cell getInstance(Position pos, Group group) {
		return new Cell(pos, -1, group);
	}

	/**
	 * Function to output the puzzle as ascii art
	 * 
	 * @param lineInCell Line of the cell currently being printed. 1<=lineInCell<=cellHeight
	 * @param cellWidth Width of the cell
	 * @param cellHeight Height of the cell
	 * @return String containing the contents of the cell on that line
	 */
	public String toAsciiString(int lineInCell, int cellWidth, int cellHeight) {
		String cellLine = "                           ";
		
		// is string most top, and if tie, most left?
		if (lineInCell == 1 && position.equals(group.getMostTopLeftPosition())) {
			cellLine = " " + group.getResult() + group.getOperation() + cellLine;
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
