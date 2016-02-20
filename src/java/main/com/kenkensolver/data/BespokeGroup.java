package com.kenkensolver.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class BespokeGroup extends Group {
	
	private final int result;
	private final Operation operation;
	
	// TODO: I think possible solutions var needs to be moved to the super class????
	private Set<List<Integer>> possibleSolutions;
	
	// TODO The issue is that the row and cell groups are all fucked up
	// they are missing most of their entries, which explains why some of the
	// cells are not being updated.
	
	public BespokeGroup(int res, Operation op) {
		super();
		result = res;
		operation = op;
		possibleSolutions = new HashSet<>();
	}

	public int getResult() {
		return result;
	}

	public Operation getOperation() {
		return operation;
	}

	public Set<List<Integer>> getPossibleSolutions() {
		return possibleSolutions;
	}
	
	public void setPossibleSolutions(Set<List<Integer>> possibleSolutions) {
		this.possibleSolutions = possibleSolutions;
	}
	
	@Override
	public String toString() {
		StringJoiner cellsJoiner = new StringJoiner(";");
		for (Cell c : getCells()) {
			cellsJoiner.add(c.getId() + c.getPosition().toString());
		}
		
		String resultAndOp = result + operation.getOperationSymbol();
		if (resultAndOp.length() < 3) {
			resultAndOp = (resultAndOp + "   ").substring(0, 3);
		}
		
		StringJoiner solutionsJoiner = new StringJoiner("");
		for (List<Integer> possibleSolution : possibleSolutions) {
			StringJoiner solutionJoiner = new StringJoiner(",");
			for (Integer value : possibleSolution) {
				solutionJoiner.add(value.toString());
			}
			solutionsJoiner.add("(" + solutionJoiner.toString() + ")");
		}
		
		return "Group={" + resultAndOp + " "
				+ "Cells={"+ cellsJoiner.toString() + "} "
				+ "PosSolns={"+solutionsJoiner+"}}";
	}
	
	@Override
	public boolean isSolutionValid(List<Integer> potentialGroupSolution) {
		boolean isValidArithmetically = operation.isValidSolution(result, potentialGroupSolution); 
		if (isValidArithmetically) {
			return super.isSolutionValid(potentialGroupSolution);
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean isSolved() {
		return possibleSolutions.size() == 1 && super.isSolved();
	}

	
	
}
