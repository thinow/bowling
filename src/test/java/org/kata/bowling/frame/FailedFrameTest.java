package org.kata.bowling.frame;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;

import java.util.Collection;

import org.junit.Test;
import org.kata.bowling.frame.Frame.Try;

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

	@Test
	public void failedFrameAsTwoTries() throws Exception {
		// given
		FailedFrame frame = new FailedFrame(FIRST_TRY, SECOND_TRY);

		// when
		Collection<Try> tries = frame.asTries();

		// then
		assertThat(tries).onProperty("pins").isEqualTo(newArrayList(FIRST_TRY, SECOND_TRY));
	}

}
