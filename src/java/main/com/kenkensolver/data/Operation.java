package com.kenkensolver.data;

import java.util.List;

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
			return args.length == 1 && result == args[0];
		}
	};
	
	private String operatorSymbol = "";
	
	private Operation() { }
	
	private Operation(String op) {
		operatorSymbol = op;
	}
	
	public String getOperationSymbol() {
		return operatorSymbol;
	}
	
	protected abstract boolean isValidSolution(int result, int... args);
 	
 	public boolean isValidSolution(int result, List<Integer> args) {
 		int[] argArray = new int[args.size()];
 		for (int i=0; i<args.size(); i++) {
 			// TODO: Put some sort of null check here and throw exception?
 			argArray[i] = args.get(i);
 		}
 		return isValidSolution(result, argArray);
 	}
 	
 	@Override
 	public String toString() {
 		return name();
 	}
 	
	protected boolean isValidSolution(int result, Operation op, OpFunction func, int... args) {
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
	protected interface OpFunction {
		public int apply(int a, int b);
	}
}
