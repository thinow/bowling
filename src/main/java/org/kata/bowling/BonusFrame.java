package org.kata.bowling;

public class BonusFrame extends Frame {

	private static final int NO_SCORE = 0;

	public BonusFrame(int knockedPins) {
		super(knockedPins);
	}

	@Override
	int getScore() {
		return NO_SCORE;
	}

}
