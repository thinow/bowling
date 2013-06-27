package org.kata.bowling;

public abstract class Frame {

	private int knockedPins;

	public Frame(int knockedPins) {
		this.knockedPins = knockedPins;
	}

	public int getKnockedPins() {
		return knockedPins;
	}

	abstract int getScore();

}
