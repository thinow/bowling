package org.kata.bowling;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.kata.bowling.exception.MandatoryNextFrameException;

public class SpareFrameTest {

	private static final int NEXT_FRAME_PINS = 6;
	private static final int SPARE_BONUS = 10;
	private static final int PINS = 4;

	@Test(expected = MandatoryNextFrameException.class)
	public void cannotGetScoreWithoutNextFrame() throws Exception {
		// given
		Frame frame = new SpareFrame(PINS);

		// when
		frame.getScore();
	}

	@Test
	public void computeScoreBasedOnNextFrame() throws Exception {
		// given
		Frame frame = new SpareFrame(PINS);
		frame.setNext(createFrame(NEXT_FRAME_PINS));

		// when
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(SPARE_BONUS + NEXT_FRAME_PINS);
	}

	private Frame createFrame(int pins) {
		Frame frame = mock(Frame.class);
		when(frame.getKnockedPins()).thenReturn(pins);

		return frame;
	}

}