package org.kata.bowling;

import static com.google.common.base.Preconditions.*;

import java.util.Collection;

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
		Collection<GameEntry> entries = parser.parseGame(game);
		for (GameEntry entry : entries) {
			frameFactory.createFrame(entry);
		}

		Collection<Frame> frames = parser.parse(game);
		return sumFrameScores(frames);
	}

	private int sumFrameScores(Collection<Frame> frames) {
		int score = 0;
		for (Frame frame : frames) {
			score += frame.getScore();
		}
		return score;
	}

}
