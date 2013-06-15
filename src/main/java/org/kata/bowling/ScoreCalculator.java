package org.kata.bowling;

import static com.google.common.base.Preconditions.*;

import java.util.Collection;

public class ScoreCalculator {

	private Parser parser;

	public ScoreCalculator(Parser parser) {
		checkNotNull(parser, "Parser cannot be null");
		this.parser = parser;
	}

	public int calculate(String game) {
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
