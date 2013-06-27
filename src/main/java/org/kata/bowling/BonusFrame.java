package org.kata.bowling;

public class BonusFrame extends Frame {

	private static final int NO_PINS = 0;
	private static final int NO_SCORE = 0;

	public BonusFrame(int knockedPins) {
		super(knockedPins, NO_PINS);
	}

	@Override
	int getScore() {
		return NO_SCORE;
	}

}
