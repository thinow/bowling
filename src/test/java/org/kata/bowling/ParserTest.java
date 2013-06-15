package org.kata.bowling;

import static com.google.common.collect.Iterables.*;
import static org.fest.assertions.Assertions.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

	private Parser parser = new Parser();

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

	private void validateFrame(Frame frame, Class<?> type, int pins) {
		assertThat(frame).isInstanceOf(type);
		assertThat(frame.getKnockedPins()).isEqualTo(pins);
	}

}
