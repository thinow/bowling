package org.kata.bowling;

import org.kata.bowling.exception.MandatoryNextFrameException;

public class SpareFrame extends Frame {

	private static final int SPARE_BONUS = 10;

	public SpareFrame(int firstTry) {
		super(firstTry);
	}

	@Override
	public int getScore() {
		if (!hasNext()) {
			throw new MandatoryNextFrameException();
		}

		return SPARE_BONUS + getNext().getKnockedPins();
	}

}
