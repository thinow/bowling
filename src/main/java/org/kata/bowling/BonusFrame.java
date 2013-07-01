package org.kata.bowling;

import static com.google.common.collect.Lists.*;

import java.util.Collection;

public class BonusFrame implements Frame {

	private static final int NO_SCORE = 0;

	private int knockedPins;

	public BonusFrame(int knockedPins) {
		this.knockedPins = knockedPins;
	}

	@Override
	public Collection<Try> asTries() {
		Try onlyOne = new Try(knockedPins);
		return newArrayList(onlyOne);
	}

	@Override
	public int getScore() {
		return NO_SCORE;
	}

}
