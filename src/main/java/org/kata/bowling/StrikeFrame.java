package org.kata.bowling;

import static com.google.common.base.Preconditions.*;

public class StrikeFrame extends Frame {

	private static final int NO_PINS = 0;
	private static final int ALL_PINS = 10;

	private static final int BONUS_STRIKE = 10;

	private Frame secondNext;
	private Frame firstNext;

	public StrikeFrame(Frame firstNext, Frame secondNext) {
		super(ALL_PINS, NO_PINS);

		checkNotNull(firstNext, "First next frame cannot be null");
		checkNotNull(secondNext, "Second next frame cannot be null");

		this.firstNext = firstNext;
		this.secondNext = secondNext;
	}

	@Override
	public int getScore() {
		int firstPins = firstNext.getKnockedPins();
		int secondPins = secondNext.getKnockedPins();
		return BONUS_STRIKE + firstPins + secondPins;
	}
}
