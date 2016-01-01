package com.kenkensolver.data;

public enum Operation {
	
	ADD("+") {
		@Override
		public int calc(int... args) {
			return calc((a, b) -> a + b, args);
		}
	},
	
	SUBTRACT("-") {
		@Override
		public int calc(int... args) {
			return calc((a, b) -> a - b, args);
		}
	},
	
	MULTIPLY("x") {
		@Override
		public int calc(int... args) {
			return calc((a, b) -> a * b, args);
		}
	},
	
	DIVIDE("/") {
		@Override
		public int calc(int... args) {
			return calc((a, b) -> (a/b)*b==a ? a/b : 0, args);
		}
	},
	
	NONE {
		@Override
		public int calc(int... args) {
			// TODO do some sort of no-op here
			return args[0];
		}
	};
	
	private String operatorStr = "";
	
	private Operation() { }
	
	private Operation(String op) {
		operatorStr = op;
	}
 
 	public abstract int calc(int... args);
 	
 	@Override
 	public String toString() {
 		return operatorStr;
 	}
 	
 	// TODO - why does this have to be a static method
	private static int calc(OpFunction func, int... args) {
		// do some type of null object checking and array checking
		int result = args[0];
		for (int i=1; i<args.length; i++) {
			result = func.apply(result, args[i]);
		}
		return result;
	}
	
	@FunctionalInterface
	interface OpFunction {
		public int apply(int a, int b);
	}
}
