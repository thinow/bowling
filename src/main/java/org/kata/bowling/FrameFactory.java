package org.kata.bowling;

import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Lists.*;
import static org.kata.bowling.GameEntry.Type.*;

import java.util.Collection;
import java.util.List;

import org.kata.bowling.GameEntry.Type;

public class FrameFactory {

	private static final GameEntry DUMMY_ENTRY = new GameEntry(FAILED, 0, 0);

	private List<GameEntry> entries;

	public Collection<Frame> createFrames(Collection<GameEntry> entries) {
		this.entries = copyOf(entries);

		int index = 0;
		List<Frame> frames = newArrayList();
		while (index < this.entries.size()) {
			frames.add(createFrame(index++));
		}

		return frames;
	}

	private Frame createFrame(int index) {
		GameEntry entry = findEntry(index);

		Frame frame = null;

		if (isLastEntry(index) && findPreviousEntry(index).getType() == SPARE) {
			frame = new BonusFrame(entry.getFirstTry());
		} else {
			frame = createFrame(entry);
		}
		return frame;
	}

	private GameEntry findPreviousEntry(int index) {
		if (index == 0) {
			return DUMMY_ENTRY;
		} else {
			return findEntry(index - 1);
		}
	}

	private GameEntry findEntry(int index) {
		return entries.get(index);
	}

	private boolean isLastEntry(int index) {
		return index == entries.size() - 1;
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
