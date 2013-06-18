package org.kata.bowling;

import static com.google.common.collect.Iterables.*;

import java.util.Collection;

public class FrameFactory {

	public Frame createFrame(GameEntry entry, Collection<Frame> previousFrames) {
		// FIXME Ne plus séparer les scores
		FailedFrame frame = new FailedFrame(entry.getPins(), 0);
		linkFrames(previousFrames, frame);

		return frame;
	}

	void linkFrames(Collection<Frame> previousFrames, Frame newFrame) {
		if (!previousFrames.isEmpty()) {
			Frame previousFrame = getLast(previousFrames);
			previousFrame.setNext(newFrame);
		}
	}

}
