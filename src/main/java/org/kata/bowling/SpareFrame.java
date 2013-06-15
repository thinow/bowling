package org.kata.bowling;

public class SpareFrame implements Frame {

	private int knockedPins;

	public SpareFrame(int firstTry) {
		this.knockedPins = firstTry;
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
