package com.kenkensolver.data;

import java.util.List;

public class CellUtils {
	
	public static boolean isSolutionOrderValid(List<Cell> cells, List<Integer> solution) {
		if (cells == null || solution == null || cells.size() != solution.size()) {
			return false;
		}
		
		// Check if this ordering is possible given possible cell values
		for (int i=0; i<solution.size(); i++) {
			Integer currValue = solution.get(i);
			Cell currCell = cells.get(i);
			
			// Check if the possible cell values line up with what's assigned
			if (!currCell.getPossibleValues().contains(currValue)) {
				return false;
			}
		}
		
		return true;
	}
	
}
