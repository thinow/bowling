package org.kata.bowling;

public class StrikeFrame implements Frame {

	private static final int ALL_PINS = 10;

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public int getKnockedPins() {
		return ALL_PINS;
	}

}
