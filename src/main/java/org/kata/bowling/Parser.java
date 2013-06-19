package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.kata.bowling.GameEntry.Type.*;

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

	private Deque<Character> parseSymbols(String game) {
		List<Character> characters = Chars.asList(game.toCharArray());
		return new LinkedList<>(characters);
	}

	private GameEntry createEntry(Deque<Character> symbols) {
		Character firstTry = symbols.pop();
		if (firstTry == SYMBOL_STRIKE) {
			return new GameEntry(STRIKE, ALL_PINS, NO_PIN);
		}

		Character secondTry = symbols.poll();
		if (secondTry != null && secondTry == SYMBOL_SPARE) {
			return new GameEntry(SPARE, integerOf(firstTry), ALL_PINS - integerOf(firstTry));
		} else {
			return new GameEntry(FAILED, integerOf(firstTry), integerOf(secondTry));
		}
	}

	private int integerOf(Character character) {
		if (character == null) {
			return NO_PIN;
		}

		switch (character) {

		case SYMBOL_MISSED:
			return NO_PIN;
		case SYMBOL_STRIKE:
			return ALL_PINS;

		default:
			return character - '0';
		}
	}

}
