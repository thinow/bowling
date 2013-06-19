package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.kata.bowling.GameEntry.Type.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.google.common.primitives.Chars;

public class Parser {

	private static final char SYMBOL_STRIKE = 'X';
	private static final char SYMBOL_SPARE = '/';
	private static final char SYMBOL_MISSED = '-';

	private static final int NO_PIN = 0;
	private static final int ALL_PINS = 10;

	public Collection<GameEntry> parse(String game) {
		Deque<Character> symbols = parseSymbols(game);

		Collection<GameEntry> entries = newArrayList();
		while (!symbols.isEmpty()) {
			GameEntry entry = createEntry(symbols);
			entries.add(entry);
		}

		return entries;
	}

	private GameEntry createEntry(Deque<Character> symbols) {
		Character symbol = symbols.pop();
		if (symbol == SYMBOL_STRIKE) {
			return new GameEntry(STRIKE, ALL_PINS, NO_PIN);
		}

		Character secondSymbol = symbols.pop();

		if (secondSymbol == SYMBOL_SPARE) {
			int firstTry = integerOf(symbol);
			return new GameEntry(SPARE, firstTry, ALL_PINS - firstTry);
		} else {
			int firstTry = integerOf(symbol);
			int secondTry = integerOf(secondSymbol);
			return new GameEntry(FAILED, firstTry, secondTry);
		}
	}

	public Collection<Frame> parseGame(String game) {
		Deque<Character> symbols = parseSymbols(game);

		Collection<Frame> frames = newArrayList();
		while (!symbols.isEmpty()) {

			if (symbols.peekFirst() == SYMBOL_STRIKE) {
				symbols.pop();

				frames.add(new StrikeFrame());

				if (symbols.size() == 2) {
					frames.add(new BonusFrame(integerOf(symbols.pop())));
					frames.add(new BonusFrame(integerOf(symbols.pop())));
				}

			} else {

				Character firstSymbol = symbols.pop();

				if (symbols.isEmpty()) {
					frames.add(new BonusFrame(integerOf(firstSymbol)));
				} else {

					Character secondSymbol = symbols.pop();

					if (secondSymbol == SYMBOL_SPARE) {
						int firstTry = integerOf(firstSymbol);
						frames.add(new SpareFrame(firstTry));
					} else {
						int firstTry = integerOf(firstSymbol);
						int secondTry = integerOf(secondSymbol);
						frames.add(new FailedFrame(firstTry + secondTry));
					}
				}
			}
		}

		linkFrames(frames);

		return frames;
	}

	private Deque<Character> parseSymbols(String game) {
		List<Character> characters = Chars.asList(game.toCharArray());
		return new LinkedList<>(characters);
	}

	private int integerOf(char character) {
		switch (character) {

		case SYMBOL_MISSED:
			return NO_PIN;
		case SYMBOL_STRIKE:
			return ALL_PINS;

		default:
			return character - '0';
		}
	}

	private void linkFrames(Collection<Frame> frames) {
		Frame previous = null;

		for (Frame frame : frames) {
			if (previous != null) {
				previous.setNext(frame);
			}
			previous = frame;
		}
	}

}
