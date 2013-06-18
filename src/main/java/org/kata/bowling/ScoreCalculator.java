package org.kata.bowling;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Collections2.*;

import java.util.Collection;

import com.google.common.base.Function;

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
		Collection<Frame> frames = transform(entries, new Function<GameEntry, Frame>() {
			@Override
			public Frame apply(GameEntry entry) {
				return frameFactory.createFrame(entry);
			}
		});

		return sumFrameScores(frames);
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
