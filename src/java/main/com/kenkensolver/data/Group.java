package com.kenkensolver.data;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private final int result;
	private final Operation operation;
	private final Set<Position> positions;
	private final Set<Set<Integer>> possibleSolutions;
	
	public Group(int res, Operation op, Set<Position> pos) {
		result = res;
		operation = op;
		positions = pos;
		possibleSolutions = new HashSet<Set<Integer>>();;
	}
	
	public Group(int res, Operation op, Position... pos) {
		result = res;
		operation = op;
		positions = new HashSet<Position>();
		possibleSolutions = new HashSet<Set<Integer>>();
		
		for (Position p : pos) {
			positions.add(p);
		}
	}

	public int getResult() {
		return result;
	}

	public Operation getOperation() {
		return operation;
	}
	
	public Set<Position> getPositions() {
		return positions;
	}

	public Set<Set<Integer>> getPossibleSolutions() {
		return possibleSolutions;
	}
	
	public void removePosition(Position p) {
		positions.remove(p);
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
