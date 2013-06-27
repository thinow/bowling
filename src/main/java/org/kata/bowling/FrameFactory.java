package org.kata.bowling;

import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Lists.*;
import static org.kata.bowling.GameEntry.Type.*;

import java.util.Collection;
import java.util.List;

import org.kata.bowling.GameEntry.Type;

public class FrameFactory {

	private static final class DummyFrame extends Frame {
		private DummyFrame() {
			super(0);
		}

		@Override
		int getScore() {
			return 0;
		}
	}

	private static final int FIRST = 0;
	private static final int SECOND = 1;
	private static final int THIRD = 2;

	private static final int NO_BONUS_ZONE = 0;
	private static final int SPARE_BONUS_ZONE = 1;
	private static final int SHORT_STRIKE_BONUS_ZONE = 1;
	private static final int LONG_STRIKE_BONUS_ZONE = 2;

	private static final GameEntry DUMMY_ENTRY = new GameEntry(FAILED, 0, 0);
	private static final DummyFrame DUMMY_FRAME = new DummyFrame();

	private List<GameEntry> entries;
	private List<Frame> frames;

	private int bonusCount;

	public Collection<Frame> createFrames(Collection<GameEntry> entries) {
		this.entries = reverse(copyOf(entries));
		this.bonusCount = calculateBonusCount();

		int index = 0;
		frames = newArrayList();
		while (index < this.entries.size()) {
			frames.addAll(createFrames(index++));
		}

		return reverse(frames);
	}

	private int calculateBonusCount() {
		if (findEntry(SECOND).is(SPARE))
			return SPARE_BONUS_ZONE;
		else if (findEntry(SECOND).is(STRIKE) && findEntry(FIRST).is(SPARE))
			return SHORT_STRIKE_BONUS_ZONE;
		else if (findEntry(THIRD).is(STRIKE))
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
		return index < bonusCount;
	}

	private Collection<Frame> createFramesFromEntry(GameEntry entry) {
		Frame frame = createFrame(entry);
		return newArrayList(frame);
	}

	private Collection<? extends Frame> createBonusFramesFromEntry(GameEntry entry) {
		if (entry.is(SPARE)) {
			BonusFrame firstFrame = new BonusFrame(entry.getFirstTry());
			BonusFrame secondFrame = new BonusFrame(entry.getSecondTry());
			return newArrayList(secondFrame, firstFrame);
		} else {
			return newArrayList(new BonusFrame(entry.getFirstTry()));
		}
	}

	private Frame createFrame(GameEntry entry) {
		final Type type = entry.getType();

		switch (type) {

		case STRIKE:
			return new StrikeFrame(findLastFrame(), findSecondToLastFrame());
		case SPARE:
			return new SpareFrame(entry.getFirstTry(), findLastFrame());
		default:
			return new FailedFrame(entry.getFirstTry(), entry.getSecondTry());
		}
	}

	private Frame findLastFrame() {
		int index = frames.size() - 1;
		return findFrame(index);
	}

	private Frame findSecondToLastFrame() {
		int index = frames.size() - 2;
		return findFrame(index);
	}

	private Frame findFrame(int index) {
		if (index < 0) {
			return DUMMY_FRAME;
		} else {
			return frames.get(index);
		}
	}

}
