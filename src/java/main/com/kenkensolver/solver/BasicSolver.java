package com.kenkensolver.solver;

import java.util.HashSet;
import java.util.Set;

import com.kenkensolver.data.BespokeGroup;
import com.kenkensolver.data.Cell;
import com.kenkensolver.data.Operation;
import com.kenkensolver.data.Position;
import com.kenkensolver.data.Puzzle;

public class BasicSolver implements Solver {

	@Override
	public void solve(Puzzle p) {
		
		// Hydrate cells with possible values
		hydrateAllCellsWithPossibleValues(p);
		
		// Get all groups with NONE operator and assign the cell with that value
		processAllGroupsWithOneCell(p);
		
		// process the groups
		processGroupsGenSolutions(p);
		
		System.out.println(p.toStringAscii());
		System.out.println(p.toStringDetailed());
		
		int puzzlePass=1;
		
		while (!p.isSolved()) {
			
			for (BespokeGroup bg : p.getBespokeGroups()) {
				// put conditional around a solved group
				bg.refineSolution(p);
//				System.out.println(p.toStringDetailed());
//				System.out.println(p.toStringAscii());
//				System.out.print("");
			}
			
			for (Cell c : p.getAllCells()) {
				c.updateCellsInSameRowAndColumn();
			}
			
			int cellsSolved = 0;
			for (Cell c : p.getAllCells()) {
				if (c.isSolved()) {
					cellsSolved++;
				}
			}
			
			System.out.print(p.toStringDetailed());
			System.out.print(p.toStringAscii());
			
			System.out.println("puzzlePass= " + puzzlePass++
					+ " cellsSolved=" + cellsSolved++);
			
			System.out.print("");
			
		}
		
	}

	private void processGroupsGenSolutions(Puzzle p) {
		// Go through each group and figure out solutions
		for (BespokeGroup bg : p.getBespokeGroups()) {
			
			// Generate all possible solutions for that group
			bg.generatePossibleSolutions();
			
		}
	}

	private void processAllGroupsWithOneCell(Puzzle puzzle) {
		for (BespokeGroup bg : puzzle.getBespokeGroups()) {
			if (Operation.NONE.equals(bg.getOperation())) {
				int cellValue = bg.getResult();
				
				for (Position pos : bg.getPositions()) {
					// There should only be one position for this type of group
					
					// Get cell at this position
					Cell cell = puzzle.getPositionCellMap().get(pos);
					
					// Make the groups result the cell's only possible value
					cell.getPossibleValues().clear();
					cell.getPossibleValues().add(cellValue);
					
					// Remove that value as a possible value from all other cells in that 
					// same row and column
					Set<Cell> rowAndColCells = new HashSet<Cell>();
					rowAndColCells.addAll(puzzle.getAllCellsInSameRow(cell));
					rowAndColCells.addAll(puzzle.getAllCellsInSameColumn(cell));
					rowAndColCells.remove(cell);
					for (Cell c : rowAndColCells) {
						c.removeValueFromPossible(cellValue);
					}
					
				}
			}
		}
	}

	private void hydrateAllCellsWithPossibleValues(Puzzle p) {
		Set<Integer> possibleCellValues = new HashSet<Integer>();
		
		for (int i=1; i<=p.getSize(); i++) {
			possibleCellValues.add(i);
		}
		
		for (Cell c : p.getAllCells()) {
			c.getPossibleValues().addAll(possibleCellValues);
		}
	}
	
	/*
	 * TODO: Read this...
	 * 
	 * GOT IT
	 * 
	 * Find possible solution(s) for a group
	 * iterate through all cells and update based on those possibilities.
	 */
	
	/*
	 * Start with puzzle
	 * Iterate through cells
	 * - populate with possible values
	 * 
	 */
	
	/*
	 * The base case to stop repeating the processing is when all the cells have only 
	 * one possible value left.
	 * 
	 * Per bespoke group
	 * - Generate all possible solutions
	 *     - Solutions have same length as number of positions in group
	 *     - Solutions have uniqueness limit based on how many rows/columns the group covers
	 * 
	 * Take group
	 * Get positions
	 * Get cells
	 * Take cross product of the possible values for each cell
	 * That will generate all possible solutions for that group
	 * - test solutions against uniqueness constraints
	 * - test solutions against operation and result
	 * - keep only those that pass both tests
	 * 
	 */

}
