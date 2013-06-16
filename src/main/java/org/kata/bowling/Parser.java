package org.kata.bowling;

import static com.google.common.collect.Lists.*;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

import com.google.common.primitives.Chars;

public class Parser {

	private static final char SYMBOL_STRIKE = 'X';
	private static final char SYMBOL_SPARE = '/';
	private static final char SYMBOL_MISSED = '-';

	public Collection<Frame> parse(String game) {

		Deque<Character> symbols = new LinkedList<>(Chars.asList(game.toCharArray()));

		Collection<Frame> frames = newArrayList();
		frames.add(parseFrame(symbols));
		return frames;
	}

	private Frame parseFrame(Deque<Character> symbols) {

		if (symbols.peekFirst() == SYMBOL_STRIKE) {
			symbols.pop();
			return new StrikeFrame();
		}

		Character firstSymbol = symbols.pop();
		Character secondSymbol = symbols.pop();

		if (secondSymbol == SYMBOL_SPARE) {
			int firstTry = integerOf(firstSymbol);
			return new SpareFrame(firstTry);
		} else {
			int firstTry = integerOf(firstSymbol);
			int secondTry = integerOf(secondSymbol);
			return new FailedFrame(firstTry, secondTry);
		}
	}

	private int integerOf(char character) {
		if (character == SYMBOL_MISSED) {
			return 0;
		} else {
			return character - '0';
		}
	}

}
