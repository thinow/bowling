package org.kata.bowling;

import static com.google.common.base.Preconditions.*;

public class SpareFrame extends Frame {

	private static final int ALL_PINS = 10;

	private static final int SPARE_BONUS = 10;

	private Frame next;

	public SpareFrame(int firstTry, Frame next) {
		super(ALL_PINS);

		checkNotNull(next, "Next frame cannot be null");
		this.next = next;
	}

	@Override
	public int getScore() {
		return SPARE_BONUS + next.getKnockedPins();
	}

}
