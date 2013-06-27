package org.kata.bowling;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class StrikeFrameTest {

	private static final int STRIKE_BONUS = 10;
	private static final int FIRST_FRAME_PINS = 2;
	private static final int SECOND_FRAME_PINS = 6;

	@Test(expected = NullPointerException.class)
	public void cannotGetScoreWithoutFirstNextFrame() throws Exception {
		// when / then
		new StrikeFrame(null, createFrame(SECOND_FRAME_PINS));
	}

	@Test(expected = NullPointerException.class)
	public void cannotGetScoreWithoutSecondNextFrame() throws Exception {
		// when / then
		new StrikeFrame(createFrame(FIRST_FRAME_PINS), null);
	}

	@Test
	public void computeScoreBasedOnNextFrames() throws Exception {
		// given
		Frame firstNext = createFrame(FIRST_FRAME_PINS);
		Frame secondNext = createFrame(SECOND_FRAME_PINS);
		StrikeFrame frame = new StrikeFrame(firstNext, secondNext);

		// when
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(STRIKE_BONUS + FIRST_FRAME_PINS + SECOND_FRAME_PINS);
	}

	private Frame createFrame(int pins) {
		Frame next = mock(Frame.class);
		when(next.getKnockedPins()).thenReturn(pins);

		return next;
	}

}
