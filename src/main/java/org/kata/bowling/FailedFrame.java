package org.kata.bowling;

import static com.google.common.collect.Lists.*;

import java.util.Collection;

public class FailedFrame implements Frame {

	private int firstTry;
	private int secondTry;

	public FailedFrame(int firstTry, int secondTry) {
		this.firstTry = firstTry;
		this.secondTry = secondTry;
	}

	@Override
	public Collection<Try> asTries() {
		Try first = new Try(firstTry);
		Try second = new Try(secondTry);

		return newArrayList(first, second);
	}

	@Override
	public int getScore() {
		return firstTry + secondTry;
	}

}
