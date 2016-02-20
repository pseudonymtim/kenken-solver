package com.kenkensolver.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CellUtilsTest {
	
	@Test
	public void testIsSolutionOrderValid_BothArgsNull() {
		assertFalse(CellUtils.isSolutionOrderValid(null, null));
	}
	
	@Test
	public void testIsSolutionOrderValid_NullCellList() {
		assertFalse(CellUtils.isSolutionOrderValid(null, new ArrayList<Integer>()));
	}
	
	@Test
	public void testIsSolutionOrderValid_NullSolutionList() {
		assertFalse(CellUtils.isSolutionOrderValid(new ArrayList<Cell>(), null));
	}
	
	@Test
	public void testIsSolutionOrderValid_EmptyLists() {
		assertTrue(CellUtils.isSolutionOrderValid(new ArrayList<Cell>(), new ArrayList<Integer>()));
	}
	
	@Test
	public void testIsSolutionOrderValid_ListWithOneCell() {
		List<Cell> cells = new ArrayList<Cell>();
		List<Integer> solution = new ArrayList<Integer>();
		
		Cell cell = new Cell(null, null, null, null);
		cell.getPossibleValues().add(5);
		cells.add(cell);
		
		solution.add(5);
		
		assertTrue(CellUtils.isSolutionOrderValid(cells, solution));
	}
	
	@Test
	public void testIsSolutionOrderValid_ListWithTwoCells() {
		List<Cell> cells = new ArrayList<Cell>();
		List<Integer> solution = new ArrayList<Integer>();
		
		Cell cell1 = new Cell(null, null, null, null);
		cell1.getPossibleValues().add(5);
		cells.add(cell1);
		
		Cell cell2 = new Cell(null, null, null, null);
		cell2.getPossibleValues().add(2);
		cell2.getPossibleValues().add(3);
		cells.add(cell2);
		
		solution.add(5);
		solution.add(2);
		
		assertTrue(CellUtils.isSolutionOrderValid(cells, solution));
	}
	
	@Test
	public void testIsSolutionOrderValid_ListWithTwoCellsWrongOrder() {
		List<Cell> cells = new ArrayList<Cell>();
		List<Integer> solution = new ArrayList<Integer>();
		
		Cell cell1 = new Cell(null, null, null, null);
		cell1.getPossibleValues().add(4);
		cell1.getPossibleValues().add(5);
		cells.add(cell1);
		
		Cell cell2 = new Cell(null, null, null, null);
		cell2.getPossibleValues().add(2);
		cells.add(cell2);
		
		solution.add(2);
		solution.add(5);
		
		assertFalse(CellUtils.isSolutionOrderValid(cells, solution));
	}
	
	@Test
	public void testIsSolutionOrderValid_ListWithTwoCellsIncorrectValue() {
		List<Cell> cells = new ArrayList<Cell>();
		List<Integer> solution = new ArrayList<Integer>();
		
		Cell cell1 = new Cell(null, null, null, null);
		cell1.getPossibleValues().add(5);
		cells.add(cell1);
		
		Cell cell2 = new Cell(null, null, null, null);
		cell2.getPossibleValues().add(2);
		cells.add(cell2);
		
		solution.add(5);
		solution.add(4);
		
		assertFalse(CellUtils.isSolutionOrderValid(cells, solution));
	}

}
