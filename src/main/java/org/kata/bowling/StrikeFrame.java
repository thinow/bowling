package org.kata.bowling;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Lists.*;

import java.util.Collection;
import java.util.Iterator;

public class StrikeFrame extends Frame {

	private static final int ALL_PINS = 10;

	private static final int BONUS_STRIKE = 10;

	private Frame firstNext;
	private Frame secondNext;

	public StrikeFrame(Frame firstNext, Frame secondNext) {
		checkNotNull(firstNext, "First next frame cannot be null");
		checkNotNull(secondNext, "Second next frame cannot be null");

		this.firstNext = firstNext;
		this.secondNext = secondNext;
	}

	@Override
	public Collection<Try> asTries() {
		Try onlyOne = new Try(ALL_PINS);
		return newArrayList(onlyOne);
	}

	@Override
	public int getScore() {
		return BONUS_STRIKE + sumOfNextTries();
	}

	private int sumOfNextTries() {
		Iterable<Try> tries = findTwoFirstTries();
		return sum(tries);
	}

	private Iterable<Try> findTwoFirstTries() {
		Iterable<Try> all = concat(firstNext.asTries(), secondNext.asTries());

		Iterator<Try> iterator = all.iterator();
		return newArrayList(iterator.next(), iterator.next());
	}

	private int sum(Iterable<Try> tries) {
		int sum = 0;
		for (Try aTry : tries) {
			sum += aTry.getPins();
		}

		return sum;
	}
}
