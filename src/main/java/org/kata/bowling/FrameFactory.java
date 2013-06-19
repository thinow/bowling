package org.kata.bowling;

import static com.google.common.collect.Lists.*;

import java.util.ArrayList;
import java.util.Collection;

import org.kata.bowling.GameEntry.Type;

public class FrameFactory {

	public Collection<Frame> createFrames(Collection<GameEntry> entries) {
		ArrayList<Frame> frames = newArrayList();

		for (GameEntry entry : entries) {
			frames.add(createFrame(entry));
		}

		return frames;
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
