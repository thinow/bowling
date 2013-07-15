package org.kata.bowling;

import static com.google.common.base.Preconditions.*;

import java.util.Collection;

import org.kata.bowling.frame.Frame;

public class ScoreCalculator<T> {

	private static final Logger LOG = Logger.getInstance();

	private final Parser<T> parser;
	private final FrameFactory frameFactory;

	public ScoreCalculator(Parser<T> parser, FrameFactory frameFactory) {
		checkNotNull(parser, "Parser cannot be null");
		checkNotNull(frameFactory, "Factory cannot be null");

		this.parser = parser;
		this.frameFactory = frameFactory;
	}

	public int calculate(T game) {
		LOG.printNewGame(game);

		Collection<GameEntry> entries = parser.parse(game);
		Collection<Frame> frames = frameFactory.createFrames(entries);

		int score = sumFrameScores(frames);
		LOG.printFinalScore(score);

		return score;
	}

	private int sumFrameScores(Collection<Frame> frames) {
		int score = 0;
		for (Frame frame : frames) {
			LOG.printFrame(frame);

			int frameScore = frame.getScore();
			score += frameScore;

			LOG.printIntermediateScore(score);
		}

		return score;
	}

}
