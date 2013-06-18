package org.kata.bowling;

public class FailedFrame extends Frame {

	public FailedFrame(int knockedPins) {
		super(knockedPins);
	}

	@Override
	public int getScore() {
		return getKnockedPins();
	}

}
