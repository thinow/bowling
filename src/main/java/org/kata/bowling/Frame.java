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

	@Deprecated
	private Frame next;

	@Deprecated
	public Frame getNext() {
		return next;
	}

	@Deprecated
	public void setNext(Frame next) {
		this.next = next;
	}

	@Deprecated
	protected boolean hasNext() {
		return getNext() != null;
	}

}
