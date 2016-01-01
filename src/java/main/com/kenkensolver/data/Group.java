package com.kenkensolver.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Group {
	private Set<Position> positions;
	private int result;
	private Operation operation;
	
	private Group() { }
	
	private Group(int res, Operation op, Set<Position> positions) {
		this.result = res;
		this.operation = op;
		this.positions = positions;
	}
	
	public Set<Position> getPositions() {
		return positions;
	}

	public int getResult() {
		return result;
	}

	public Operation getOperation() {
		return operation;
	}

	public static class Builder {
		private Set<Position> positions;;
		private int result;
		private Operation operation;
		
		public Builder() {
			positions = new HashSet<Position>();
			result = -1;
			operation = Operation.NONE;
		}
		
		public Builder(Group group) {
			positions = group.getPositions();
			result = group.getResult();
			operation = group.getOperation();
		}
		
		public Builder result(int result) {
			this.result = result;
			return this;
		}
		
		public Builder operation(Operation op) {
			this.operation = op;
			return this;
		}
		
		public Builder positions(Position... positions) {
			this.positions.addAll(Arrays.asList(positions));
			return this;
		}
		
		public Builder removePosition(Position pos) {
			positions.remove(pos);
			return this;
		}
		
		public Group build() {
			return new Group(result, operation, positions);
		}
	}

	public Position getMostTopLeftPosition() {
		Position mostTopLeft = null;
		
		for (Position p : positions) {
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
