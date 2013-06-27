package org.kata.bowling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class Frame {

	@Getter
	private int firstTry;
	@Getter
	private int secondTry;

	public int getKnockedPins() {
		return firstTry + secondTry;
	}

	abstract int getScore();
}
