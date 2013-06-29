package org.kata.bowling;

import static com.google.common.base.Preconditions.*;

import java.util.Collection;

public class ScoreCalculator {

	private static final Logger LOG = Logger.getInstance();

	private final Parser parser;
	private final FrameFactory frameFactory;

	public ScoreCalculator(Parser parser, FrameFactory frameFactory) {
		checkNotNull(parser, "Parser cannot be null");
		checkNotNull(frameFactory, "Factory cannot be null");

		this.parser = parser;
		this.frameFactory = frameFactory;
	}

	public int calculate(String game) {
		LOG.printNewGame(game);

		Collection<GameEntry> entries = parser.parse(game);
		Collection<Frame> frames = frameFactory.createFrames(entries);

		return sumFrameScores(frames);
	}

	private int sumFrameScores(Collection<Frame> frames) {
		int score = 0;
		for (Frame frame : frames) {
			LOG.printFrame(frame);

			int frameScore = frame.getScore();
			score += frameScore;
		}
		return score;
	}

}
