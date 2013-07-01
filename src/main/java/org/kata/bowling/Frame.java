package org.kata.bowling;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class Frame {

	@AllArgsConstructor
	public static class Try {
		@Getter
		private int pins;
	}

	@Getter
	private int firstTry;
	@Getter
	private int secondTry;

	public int getKnockedPins() {
		return firstTry + secondTry;
	}

	abstract int getScore();

	public Collection<Try> asTries() {
		return null;
	}

}
