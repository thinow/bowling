package org.kata.bowling;

import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Lists.*;
import static org.kata.bowling.GameEntry.Type.*;

import java.util.Collection;
import java.util.List;

import org.kata.bowling.GameEntry.Type;

public class FrameFactory {

	private static final int NO_BONUS_ZONE = 0;
	private static final int SPARE_BONUS_ZONE = 1;
	private static final int SHORT_STRIKE_BONUS_ZONE = 1;
	private static final int LONG_STRIKE_BONUS_ZONE = 2;

	private static final GameEntry DUMMY_ENTRY = new GameEntry(FAILED, 0, 0);

	private List<GameEntry> entries;
	private int bonusCount;

	public Collection<Frame> createFrames(Collection<GameEntry> entries) {
		this.entries = copyOf(entries);
		this.bonusCount = calculateBonusCount();

		int index = 0;
		List<Frame> frames = newArrayList();
		while (index < this.entries.size()) {
			frames.addAll(createFrames(index++));
		}

		return frames;
	}

	private int calculateBonusCount() {
		int last = entries.size() - 1;
		int secondToLast = last - 1;
		int thirdToLast = last - 2;

		if (findEntry(secondToLast).is(SPARE))
			return SPARE_BONUS_ZONE;
		else if (findEntry(secondToLast).is(STRIKE) && findEntry(last).is(SPARE))
			return SHORT_STRIKE_BONUS_ZONE;
		else if (findEntry(thirdToLast).is(STRIKE))
			return LONG_STRIKE_BONUS_ZONE;
		else
			return NO_BONUS_ZONE;
	}

	private Collection<? extends Frame> createFrames(int index) {
		GameEntry entry = findEntry(index);

		if (isBonusZone(index)) {
			return createBonusFramesFromEntry(entry);
		} else {
			return createFramesFromEntry(entry);
		}
	}

	private GameEntry findEntry(int index) {
		boolean validIndex = 0 <= index && index < entries.size();

		if (!validIndex)
			return DUMMY_ENTRY;
		else
			return entries.get(index);
	}

	private boolean isBonusZone(int index) {
		int remainingEntries = entries.size() - (index + 1);
		return remainingEntries < bonusCount;
	}

	private Collection<Frame> createFramesFromEntry(GameEntry entry) {
		Frame frame = createFrame(entry);
		return newArrayList(frame);
	}

	private Collection<? extends Frame> createBonusFramesFromEntry(GameEntry entry) {
		if (entry.is(SPARE)) {
			BonusFrame firstFrame = new BonusFrame(entry.getFirstTry());
			BonusFrame secondFrame = new BonusFrame(entry.getSecondTry());
			return newArrayList(firstFrame, secondFrame);
		} else {
			return newArrayList(new BonusFrame(entry.getFirstTry()));
		}
	}

	private Frame createFrame(GameEntry entry) {
		final Type type = entry.getType();
		final int pins = entry.getPins();

		switch (type) {

		case FAILED:
			return new FailedFrame(pins);
		case SPARE:
			return new SpareFrame(pins);
		case STRIKE:
			return new StrikeFrame();

		default:
			return null;
		}
	}

}
