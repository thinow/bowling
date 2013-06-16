package org.kata.bowling;

public class StrikeFrame extends Frame {

	private static final int ALL_PINS = 10;

	public StrikeFrame() {
		super(ALL_PINS);
	}

	@Override
	public int getScore() {
		return 0;
	}

}
