package com.kenkensolver.data;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

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
	
}
