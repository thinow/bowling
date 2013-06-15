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
		assertThat(getOnlyElement(frames)).isInstanceOf(StrikeFrame.class);
	}

}
