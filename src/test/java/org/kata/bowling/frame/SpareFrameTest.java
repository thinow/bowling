package org.kata.bowling.frame;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.MockedFrameHelper.*;

import org.junit.Test;
import org.kata.bowling.frame.SpareFrame;

public class SpareFrameTest {

	private static final int ALL_PINS = 10;
	private static final int FIRST_TRY = 4;

	private static final int NEXT_SHOT = 1;
	private static final int SPARE_BONUS = 10;

	@Test(expected = NullPointerException.class)
	public void cannotGetScoreWithoutNextFrame() throws Exception {
		// when / then
		new SpareFrame(FIRST_TRY, null);
	}

	@Test
	public void computeScoreBasedOnNextFrame() throws Exception {
		// given
		Frame frame = new SpareFrame(FIRST_TRY, createFrame(NEXT_SHOT));

		// when
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(SPARE_BONUS + NEXT_SHOT);
	}

	@Test
	public void spareAsTwoTries() throws Exception {
		// given
		SpareFrame frame = new SpareFrame(FIRST_TRY, anyFrame());

		// when / then
		int first = FIRST_TRY;
		int second = ALL_PINS - FIRST_TRY;
		assertThat(frame.asTries()).onProperty("pins").isEqualTo(newArrayList(first, second));
	}
}
