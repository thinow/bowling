package org.kata.bowling;

public class SpareFrame implements Frame {

	private int knockedPins;

	public SpareFrame(int knockedPins) {
		this.knockedPins = knockedPins;
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
