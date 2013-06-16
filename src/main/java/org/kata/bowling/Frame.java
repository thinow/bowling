package org.kata.bowling;

public abstract class Frame {

	private int knockedPins;
	private Frame next;

	public Frame(int knockedPins) {
		this.knockedPins = knockedPins;
	}

	public final int getKnockedPins() {
		return knockedPins;
	}

	abstract int getScore();

	public Frame getNext() {
		return next;
	}

	public void setNext(Frame next) {
		this.next = next;
	}

}
