package org.kata.bowling;

import org.kata.bowling.exception.MandatoryNextFrameException;

public class StrikeFrame extends Frame {

	private static final int ALL_PINS = 10;
	private static final int BONUS_STRIKE = 10;

	public StrikeFrame() {
		super(ALL_PINS);
	}

	@Override
	public int getScore() {
		if (!this.hasNext() || !getNext().hasNext()) {
			throw new MandatoryNextFrameException();
		}

		int firstPins = getNext().getKnockedPins();
		int secondPins = getNext().getNext().getKnockedPins();
		return BONUS_STRIKE + firstPins + secondPins;
	}
}
