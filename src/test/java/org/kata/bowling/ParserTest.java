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
		assertThat(frame).isInstanceOf(StrikeFrame.class);
		assertThat(frame.getKnockedPins()).isEqualTo(10);
	}

	@Test
	public void spareFrame() throws Exception {
		// when
		Collection<Frame> frames = parser.parse("6/");

		// then
		Frame frame = getOnlyElement(frames);
		assertThat(frame).isInstanceOf(SpareFrame.class);
		assertThat(frame.getKnockedPins()).isEqualTo(6);
	}

}
