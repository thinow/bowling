package org.kata.bowling;

import static com.google.common.collect.Iterables.*;

import java.util.Collection;

import org.kata.bowling.GameEntry.Type;

public class FrameFactory {

	public Frame createFrame(GameEntry entry, Collection<Frame> previousFrames) {
		Frame frame = createFrame(entry);
		linkFrames(previousFrames, frame);

		return frame;
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

	void linkFrames(Collection<Frame> previousFrames, Frame newFrame) {
		if (!previousFrames.isEmpty()) {
			Frame previousFrame = getLast(previousFrames);
			previousFrame.setNext(newFrame);
		}
	}

	public Collection<Frame> createFrames(Collection<GameEntry> entries) {
		return null;
	}

}
