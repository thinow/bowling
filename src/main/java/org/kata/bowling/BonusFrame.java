package org.kata.bowling;

public class BonusFrame extends Frame {

	public BonusFrame(int knockedPins) {
		super(knockedPins);
	}

	@Override
	int getScore() {
		return getKnockedPins();
	}

}
