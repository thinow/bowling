package org.kata.bowling;

public class SpareFrame extends Frame {

	public SpareFrame(int firstTry) {
		super(firstTry);
	}

	@Override
	public int getScore() {
		return 0;
	}

}
