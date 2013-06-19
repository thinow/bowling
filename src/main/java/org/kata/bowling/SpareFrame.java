package org.kata.bowling;

import org.kata.bowling.exception.MandatoryNextFrameException;

public class SpareFrame extends Frame {

	private static final int ALL_PINS = 10;

	private static final int SPARE_BONUS = 10;

	public SpareFrame(int firstTry) {
		super(ALL_PINS);
	}

	@Override
	public int getScore() {
		if (!hasNext()) {
			throw new MandatoryNextFrameException();
		}

		return SPARE_BONUS + getNext().getKnockedPins();
	}

}
