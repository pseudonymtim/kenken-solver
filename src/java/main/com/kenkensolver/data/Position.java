package com.kenkensolver.data;

public final class Position {
	private final int rowIndex;
	private final int colIndex;
	
	private Position(int rowIdx, int colIdx) {
		rowIndex = rowIdx;
		colIndex = colIdx;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		else if (!(o instanceof Position)) {
			return false;
		}
		else {
			Position pos = (Position)o;
			return rowIndex == pos.getRowIndex()
					&& colIndex == pos.getColIndex();
		}
	}
	
	@Override
	public int hashCode() {
		String rowStr = String.valueOf(rowIndex);
		String colStr = String.valueOf(colIndex);
		return Integer.parseInt(rowStr + colStr);
	}
	
	@Override
	public String toString() {
		return "(" + colIndex + "," + rowIndex + ")";
	}
	
	public static Position getInstance(int rowIndex, int colIndex) {
		// TODO add throw exception here if args are passed in as 0 or negative
		return new Position(rowIndex, colIndex);
	}
}
