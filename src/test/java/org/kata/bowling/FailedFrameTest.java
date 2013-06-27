package org.kata.bowling;

import static org.fest.assertions.Assertions.*;

import org.junit.Test;

public class FailedFrameTest {

	private static final int FIRST_TRY = 4;
	private static final int SECOND_TRY = 4;

	@Test
	public void scoreIsKnockedPinsCount() throws Exception {
		// given
		FailedFrame frame = new FailedFrame(FIRST_TRY, SECOND_TRY);

		// when
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(FIRST_TRY + SECOND_TRY);
	}

}
