package org.kata.bowling;

public class FailedFrame extends Frame {

	public FailedFrame(int firstTry, int secondTry) {
		super(firstTry, secondTry);
	}

	@Override
	public int getScore() {
		return getKnockedPins();
	}

}
