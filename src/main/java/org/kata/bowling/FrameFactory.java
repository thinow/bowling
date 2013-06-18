package org.kata.bowling;

public class FrameFactory {

	public Frame createFrame(GameEntry entry) {
		return new FailedFrame(entry.getPins(), 0); // FIXME Ne plus séparer les scores
	}

}
