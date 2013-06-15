package org.kata.bowling;

public class FailedFrame implements Frame {

	private int knockedPins;

	public FailedFrame(int firstTry, int secondTry) {
		this.knockedPins = firstTry + secondTry;
	}

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public int getKnockedPins() {
		return knockedPins;
	}

}
