package com.kenkensolver.data;

public final class Position implements Comparable<Position>{
	
	private final int rowIndex;
	private final int colIndex;
	
	public Position(int rowIdx, int colIdx) {
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
		return "(" + rowIndex + "," + colIndex + ")";
	}

	@Override
	public int compareTo(Position p) {
		if (p == null) {
			return -1;
		}
		else if (this.getRowIndex() == p.getRowIndex()) {
			return this.getColIndex() - p.getColIndex();
		}
		else {
			return this.getRowIndex() - p.getRowIndex();
		}
	}
}
