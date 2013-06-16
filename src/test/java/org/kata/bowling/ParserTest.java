package org.kata.bowling;

import static com.google.common.collect.Iterables.*;
import static org.fest.assertions.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

	private Parser parser = new Parser();

	private Iterator<Frame> framesIterator;

	@Test
	public void strikeFrame() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("X");

		// then
		Frame frame = getOnlyElement(frames);
		validateFrame(frame, StrikeFrame.class, 10);
	}

	@Test
	public void spareFrame() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("6/");

		// then
		Frame frame = getOnlyElement(frames);
		validateFrame(frame, SpareFrame.class, 6);
	}

	@Test
	public void doubleScoredFrame() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("36");

		// then
		Frame frame = getOnlyElement(frames);
		validateFrame(frame, FailedFrame.class, 3 + 6);
	}

	@Test
	public void scoreAndMissFrame() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("4-");

		// then
		Frame frame = getOnlyElement(frames);
		validateFrame(frame, FailedFrame.class, 4);
	}

	@Test
	public void parseFrameSuite() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("X" + "8/" + "9-");

		// then
		assertThat(frames).hasSize(3);
		validateFrame(nextOf(frames), StrikeFrame.class, 10);
		validateFrame(nextOf(frames), SpareFrame.class, 8);
		validateFrame(nextOf(frames), FailedFrame.class, 9);
	}

	@Test
	public void parseSuiteWithSpareAndBonus() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("2/" + "5");

		// then
		assertThat(frames).hasSize(2);
		validateFrame(nextOf(frames), SpareFrame.class, 2);
		validateFrame(nextOf(frames), BonusFrame.class, 5);
	}

	@Test
	public void parseSuiteWithStrikeAndTwoBonus() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("X" + "5" + "3");

		// then
		assertThat(frames).hasSize(3);
		validateFrame(nextOf(frames), StrikeFrame.class, 10);
		validateFrame(nextOf(frames), BonusFrame.class, 5);
		validateFrame(nextOf(frames), BonusFrame.class, 3);
	}

	@Test
	public void parseSuiteWithStrikeAndTwoStikeBonus() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("X" + "X" + "X");

		// then
		assertThat(frames).hasSize(3);
		validateFrame(nextOf(frames), StrikeFrame.class, 10);
		validateFrame(nextOf(frames), BonusFrame.class, 10);
		validateFrame(nextOf(frames), BonusFrame.class, 10);
	}

	@Test
	public void framesAreLinkedToEachOther() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("1-" + "2-" + "3-");

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
