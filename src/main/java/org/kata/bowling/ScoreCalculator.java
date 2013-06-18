package org.kata.bowling;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Lists.*;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class ScoreCalculator {

	private final Parser parser;
	private final FrameFactory frameFactory;

	public ScoreCalculator(Parser parser, FrameFactory frameFactory) {
		checkNotNull(parser, "Parser cannot be null");
		checkNotNull(frameFactory, "Factory cannot be null");

		this.parser = parser;
		this.frameFactory = frameFactory;
	}

	public int calculate(String game) {
		Collection<GameEntry> entries = parser.parse(game);
		Collection<Frame> frames = transformEntriesIntoFrames(entries);

		return sumFrameScores(frames);
	}

	private Collection<Frame> transformEntriesIntoFrames(Collection<GameEntry> entries) {
		Collection<Frame> frames = newArrayList();

		for (GameEntry entry : entries) {
			Frame frame = frameFactory.createFrame(entry, copyOf(frames));
			frames.add(frame);
		}

		return frames;
	}

	private Collection<Frame> copyOf(Collection<Frame> frames) {
		return ImmutableList.copyOf(frames);
	}

	private int sumFrameScores(Collection<Frame> frames) {
		int score = 0;
		for (Frame frame : frames) {
			int frameScore = frame.getScore();
			score += frameScore;
		}
		return score;
	}

}
