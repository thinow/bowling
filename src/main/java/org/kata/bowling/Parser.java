package org.kata.bowling;

import static com.google.common.collect.Lists.*;

import java.util.Collection;

public class Parser {

	private static final String SYMBOL_STRIKE = "X";

	public Collection<Frame> parse(String game) {
		Collection<Frame> frames = newArrayList();
		frames.add(parseFrame(game));
		return frames;
	}

	private Frame parseFrame(String game) {
		if (SYMBOL_STRIKE.equals(game)) {
			return new StrikeFrame();
		} else {
			char pinsCount = findFirstCharacter(game);
			return new SpareFrame(integerOf(pinsCount));
		}
	}

	private char findFirstCharacter(String text) {
		return text.charAt(0);
	}

	private int integerOf(char character) {
		return character - '0';
	}

}
