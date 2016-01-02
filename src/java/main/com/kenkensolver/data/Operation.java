package com.kenkensolver.data;

import java.util.HashSet;
import java.util.Set;

public enum Operation {
	
	ADD("+") {
		@Override
		public boolean isValidSolution(int result, int... args) {
			return isValidSolution(result, this, (a, b) -> a + b, args);
		}
	},
	
	SUBTRACT("-") {
		@Override
		public boolean isValidSolution(int result, int... args) {
			return isValidSolution(result, this, (a, b) -> a - b, args);
		}
	},
	
	MULTIPLY("x") {
		@Override
		public boolean isValidSolution(int result, int... args) {
			return isValidSolution(result, this, (a, b) -> a * b, args);
		}
	},
	
	DIVIDE("/") {
		@Override
		public boolean isValidSolution(int result, int... args) {
			return isValidSolution(result, this, (a, b) -> (a/b)*b==a ? a/b : 0, args);
		}
	},
	
	NONE {
		@Override
		public boolean isValidSolution(int result, int... args) {
			return args.length == 1;
		}
	},
	
	UNIQUE {
		@Override
		public boolean isValidSolution(int result, int... args) {
			Set<Integer> argSet = new HashSet<Integer>();
			for (int i : args) {
				argSet.add(i);
			}
			return args.length == argSet.size();
		}
	};
	
	private String operatorStr = "";
	
	private Operation() { }
	
	private Operation(String op) {
		operatorStr = op;
	}
 
 	public abstract boolean isValidSolution(int result, int... args);
 	
 	@Override
 	public String toString() {
 		return operatorStr;
 	}
 	
	private static boolean isValidSolution(int result, Operation op, OpFunction func, int... args) {
		int[] values = args.clone();
		
		if (Operation.SUBTRACT.equals(op) || Operation.DIVIDE.equals(op)) {
			// Argument order matters for these operations
			
			// Find largest argument
			int largestIndex = 0;
			for (int i=0; i<values.length; i++) {
				if (values[i] >= values[largestIndex]) {
					largestIndex = i;
				}
			}
			
			// Put largest element first
			int temp = values[0];
			values[0] = values[largestIndex];
			values[largestIndex] = temp;
		}
		
		// Perform the operation on the arguments
		int actualResult = values[0];
		for (int i=1; i<values.length; i++) {
			actualResult = func.apply(actualResult, values[i]);
		}
		
		return result == actualResult;
	}
	
	@FunctionalInterface
	interface OpFunction {
		public int apply(int a, int b);
	}
}
