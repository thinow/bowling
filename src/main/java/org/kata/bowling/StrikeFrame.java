package org.kata.bowling;

import static com.google.common.base.Preconditions.*;

public class StrikeFrame extends Frame {

	private static final int ALL_PINS = 10;
	private static final int BONUS_STRIKE = 10;
	private Frame secondNext;
	private Frame firstNext;

	@Deprecated
	public StrikeFrame() {
		super(ALL_PINS);
	}

	public StrikeFrame(Frame firstNext, Frame secondNext) {
		super(ALL_PINS);

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
