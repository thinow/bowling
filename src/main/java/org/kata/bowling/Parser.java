package org.kata.bowling;

import static com.google.common.collect.Lists.*;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.google.common.primitives.Chars;

public class Parser {

	private static final char SYMBOL_STRIKE = 'X';
	private static final char SYMBOL_SPARE = '/';
	private static final char SYMBOL_MISSED = '-';

	public Collection<Frame> parse(String game) {
		Deque<Character> symbols = parseSymbols(game);

		Collection<Frame> frames = newArrayList();
		while (!symbols.isEmpty()) {
			frames.add(parseFrame(symbols));
		}
		return frames;
	}

	private Deque<Character> parseSymbols(String game) {
		List<Character> characters = Chars.asList(game.toCharArray());
		return new LinkedList<>(characters);
	}

	private Frame parseFrame(Deque<Character> symbols) {

		if (symbols.peekFirst() == SYMBOL_STRIKE) {
			symbols.pop();
			return new StrikeFrame();
		}

		Character firstSymbol = symbols.pop();

		if (symbols.isEmpty()) {
			return new BonusFrame(integerOf(firstSymbol));
		}

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
