package com.kenkensolver.data;

public final class Position {
	private final int horizontalPos;
	private final int verticlePos;
	
	private Position(int horPos, int verPos) {
		horizontalPos = horPos;
		verticlePos = verPos;
	}
	
	public int getHorizontalPos() {
		return horizontalPos;
	}

	public int getVerticlePos() {
		return verticlePos;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		else if (!(o instanceof Position)) {
			return false;
		}
		else {
			Position pos = (Position)o;
			return this.horizontalPos == pos.getHorizontalPos()
					&& this.verticlePos == pos.getVerticlePos();
		}
	}
	
	public static Position getInstance(int horizontalPos, int verticlePos) {
		// TODO add throw exception here if args are passed in as 0 or negative
		return new Position(horizontalPos, verticlePos);
	}
}
