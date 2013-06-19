package org.kata.bowling;

import static com.google.common.collect.Iterables.*;
import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.GameEntry.Type.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kata.bowling.GameEntry.Type;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

	private static final int NO_PIN = 0;
	private static final int ALL_PINS = 10;

	private Parser parser = new Parser();

	private Iterator<GameEntry> entriesIterator;

	@Test
	public void strikeFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("X");

		// then
		GameEntry entry = getOnlyElement(entries);
		validateEntry(entry, STRIKE, ALL_PINS, NO_PIN);
	}

	@Test
	public void spareFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("6/");

		// then
		GameEntry entry = getOnlyElement(entries);
		validateEntry(entry, SPARE, 6, ALL_PINS - 6);
	}

	@Test
	public void doubleScoredFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("36");

		// then
		GameEntry entry = getOnlyElement(entries);
		validateEntry(entry, FAILED, 3, 6);
	}

	@Test
	public void scoreAndMissFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("4-");

		// then
		GameEntry entry = getOnlyElement(entries);
		validateEntry(entry, FAILED, 4, 0);
	}

	@Test
	public void parseGameFrameSuite() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("X" + "8/" + "9-");

		// then
		assertThat(entries).hasSize(3);
		validateEntry(nextOf(entries), STRIKE, ALL_PINS, NO_PIN);
		validateEntry(nextOf(entries), SPARE, 8, ALL_PINS - 8);
		validateEntry(nextOf(entries), FAILED, 9, 0);
	}

	@Test
	public void parseGameSuiteWithSpareAndBonus() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("2/" + "5");

		// then
		assertThat(entries).hasSize(2);
		validateEntry(nextOf(entries), SPARE, 2, ALL_PINS - 2);
		validateEntry(nextOf(entries), FAILED, 5, NO_PIN);
	}

	@Test
	public void parseGameSuiteWithStrikeAndTwoBonus() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("X" + "5" + "3");

		// then
		assertThat(entries).hasSize(2);
		validateEntry(nextOf(entries), STRIKE, ALL_PINS, NO_PIN);
		validateEntry(nextOf(entries), FAILED, 5, 3);
	}

	@Test
	public void parseGameSuiteWithStrikeAndTwoStikeBonus() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("X" + "X" + "X");

		// then
		assertThat(entries).hasSize(3);
		validateEntry(nextOf(entries), STRIKE, ALL_PINS, NO_PIN);
		validateEntry(nextOf(entries), STRIKE, ALL_PINS, NO_PIN);
		validateEntry(nextOf(entries), STRIKE, ALL_PINS, NO_PIN);
	}

	private GameEntry nextOf(Collection<GameEntry> entries) {
		if (entriesIterator == null) {
			entriesIterator = entries.iterator();
		}

		return entriesIterator.next();
	}

	private void validateEntry(GameEntry entry, Type type, int first, int second) {
		assertThat(entry.getType()).isEqualTo(type);
		assertThat(entry.getFirstTry()).isEqualTo(first);
		assertThat(entry.getSecondTry()).isEqualTo(second);
	}

}
