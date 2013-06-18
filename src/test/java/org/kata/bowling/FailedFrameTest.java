package org.kata.bowling;

import static org.fest.assertions.Assertions.*;

import org.junit.Test;

public class FailedFrameTest {

	private static final int PINS = 4;

	@Test
	public void scoreIsKnockedPinsCount() throws Exception {
		// given
		FailedFrame frame = new FailedFrame(PINS);

		// when
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(PINS);
	}

}
