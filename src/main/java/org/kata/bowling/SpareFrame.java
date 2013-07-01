package org.kata.bowling;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Lists.*;

import java.util.Collection;

public class SpareFrame extends Frame {

	private static final int ALL_PINS = 10;

	private static final int SPARE_BONUS = 10;

	private Frame next;

	private int firstTry;

	public SpareFrame(int firstTry, Frame next) {
		checkNotNull(next, "Next frame cannot be null");

		this.firstTry = firstTry;
		this.next = next;
	}

	@Override
	public Collection<Try> asTries() {
		Try first = new Try(firstTry);
		Try second = new Try(ALL_PINS - firstTry);

		return newArrayList(first, second);
	}

	@Override
	public int getScore() {
		Try next = findNext();
		return SPARE_BONUS + next.getPins();
	}

	private Try findNext() {
		Collection<Try> tries = next.asTries();
		return tries.iterator().next();
	}

}
