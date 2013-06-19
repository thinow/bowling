package org.kata.bowling;

import static com.google.common.collect.Iterables.*;
import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.GameEntry.Type.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

	private static final int NO_PIN = 0;
	private static final int ALL_PINS = 10;

	private Parser parser = new Parser();

	private Iterator<Frame> framesIterator;

	@Test
	public void strikeFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("X");

		// then
		GameEntry entry = getOnlyElement(entries);
		assertThat(entry.getType()).isEqualTo(STRIKE);
		assertThat(entry.getFirstTry()).isEqualTo(ALL_PINS);
		assertThat(entry.getSecondTry()).isEqualTo(NO_PIN);
	}

	@Test
	public void spareFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("6/");

		// then
		GameEntry entry = getOnlyElement(entries);
		assertThat(entry.getType()).isEqualTo(SPARE);
		assertThat(entry.getFirstTry()).isEqualTo(6);
		assertThat(entry.getSecondTry()).isEqualTo(ALL_PINS - 6);
	}

	@Test
	public void doubleScoredFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("36");

		// then
		GameEntry entry = getOnlyElement(entries);
		assertThat(entry.getType()).isEqualTo(FAILED);
		assertThat(entry.getFirstTry()).isEqualTo(3);
		assertThat(entry.getSecondTry()).isEqualTo(6);
	}

	@Test
	public void scoreAndMissFrame() throws Exception {
		// when
		Collection<GameEntry> entries = parser.parse("4-");

		// then
		GameEntry entry = getOnlyElement(entries);
		assertThat(entry.getType()).isEqualTo(FAILED);
		assertThat(entry.getFirstTry()).isEqualTo(4);
		assertThat(entry.getSecondTry()).isEqualTo(0);
	}

	@Test
	@Ignore
	public void parseGameFrameSuite() throws Exception {
		// when
		Collection<Frame> frames = parser.parseGame("X" + "8/" + "9-");

		// then
		assertThat(frames).hasSize(3);
		validateFrame(nextOf(frames), StrikeFrame.class, 10);
		validateFrame(nextOf(frames), SpareFrame.class, 8);
		validateFrame(nextOf(frames), FailedFrame.class, 9);
	}

	@Test
	@Ignore
	public void parseGameSuiteWithSpareAndBonus() throws Exception {
		// when
		Collection<Frame> frames = parser.parseGame("2/" + "5");

		// then
		assertThat(frames).hasSize(2);
		validateFrame(nextOf(frames), SpareFrame.class, 2);
		validateFrame(nextOf(frames), BonusFrame.class, 5);
	}

	@Test
	@Ignore
	public void parseGameSuiteWithStrikeAndTwoBonus() throws Exception {
		// when
		Collection<Frame> frames = parser.parseGame("X" + "5" + "3");

		// then
		assertThat(frames).hasSize(3);
		validateFrame(nextOf(frames), StrikeFrame.class, 10);
		validateFrame(nextOf(frames), BonusFrame.class, 5);
		validateFrame(nextOf(frames), BonusFrame.class, 3);
	}

	@Test
	@Ignore
	public void parseGameSuiteWithStrikeAndTwoStikeBonus() throws Exception {
		// when
		Collection<Frame> frames = parser.parseGame("X" + "X" + "X");

		// then
		assertThat(frames).hasSize(3);
		validateFrame(nextOf(frames), StrikeFrame.class, 10);
		validateFrame(nextOf(frames), BonusFrame.class, 10);
		validateFrame(nextOf(frames), BonusFrame.class, 10);
	}

	@Test
	@Ignore
	public void framesAreLinkedToEachOther() throws Exception {
		// when
		Collection<Frame> frames = parser.parseGame("1-" + "2-" + "3-");

		// then
		assertThat(frames).hasSize(3);

		Frame first = nextOf(frames);
		Frame second = nextOf(frames);
		Frame third = nextOf(frames);

		assertThat(first.getNext()).isEqualTo(second);
		assertThat(second.getNext()).isEqualTo(third);
	}

	private Frame nextOf(Collection<Frame> frames) {
		if (framesIterator == null) {
			framesIterator = frames.iterator();
		}

		return framesIterator.next();
	}

	private void validateFrame(Frame frame, Class<?> type, int pins) {
		assertThat(frame).isInstanceOf(type);
		assertThat(frame.getKnockedPins()).isEqualTo(pins);
	}

}
