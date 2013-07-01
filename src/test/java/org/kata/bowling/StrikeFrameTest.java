package org.kata.bowling;

import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.MockedFrameHelper.*;

import java.util.Collection;

import org.junit.Test;
import org.kata.bowling.Frame.Try;

public class StrikeFrameTest {

	private static final int ALL_PINS = 10;

	private static final int STRIKE_BONUS = 10;

	private static final int FIRST_TRY = 2;
	private static final int SECOND_TRY = 6;
	private static final Integer THIRD_TRY = 4;

	@Test(expected = NullPointerException.class)
	public void cannotGetScoreWithoutFirstNextFrame() throws Exception {
		// when / then
		new StrikeFrame(null, createFrame(SECOND_TRY));
	}

	@Test(expected = NullPointerException.class)
	public void cannotGetScoreWithoutSecondNextFrame() throws Exception {
		// when / then
		new StrikeFrame(createFrame(FIRST_TRY), null);
	}

	@Test
	public void computeScoreBasedOnNextTries() throws Exception {
		// given
		Frame firstNext = createFrame(FIRST_TRY, SECOND_TRY);
		Frame secondNext = createFrame(THIRD_TRY);

		// when
		StrikeFrame frame = new StrikeFrame(firstNext, secondNext);
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(STRIKE_BONUS + FIRST_TRY + SECOND_TRY);
	}

	@Test
	public void computeScoreBasedOnNextFrames() throws Exception {
		// given
		Frame firstNext = createFrame(FIRST_TRY);
		Frame secondNext = createFrame(SECOND_TRY, THIRD_TRY);

		// when
		StrikeFrame frame = new StrikeFrame(firstNext, secondNext);
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(STRIKE_BONUS + FIRST_TRY + SECOND_TRY);
	}

	@Test
	public void strikeAsOnlyOneTry() throws Exception {
		// given
		StrikeFrame frame = new StrikeFrame(anyFrame(), anyFrame());

		// when
		Collection<Try> tries = frame.asTries();

		// then
		assertThat(tries).hasSize(1);
		assertThat(tries).onProperty("pins").containsOnly(ALL_PINS);
	}

}
