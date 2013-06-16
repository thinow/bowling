package org.kata.bowling;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.kata.bowling.exception.MandatoryNextFrameException;

public class StrikeFrameTest {

	private static final int STRIKE_BONUS = 10;
	private static final int FIRST_FRAME_PINS = 2;
	private static final int SECOND_FRAME_PINS = 6;

	@Test(expected = MandatoryNextFrameException.class)
	public void cannotGetScoreWithoutNextFrame() throws Exception {
		// given
		StrikeFrame frame = new StrikeFrame();

		// when
		frame.getScore();
	}

	@Test(expected = MandatoryNextFrameException.class)
	public void cannotGetScoreWithOnlyOneNextFrame() throws Exception {
		// given
		StrikeFrame frame = new StrikeFrame();
		frame.setNext(mock(Frame.class));

		// when
		frame.getScore();
	}

	@Test
	public void computeScoreBasedOnNextFrames() throws Exception {
		// given
		StrikeFrame frame = new StrikeFrame();
		Frame firstNext = createFrame(FIRST_FRAME_PINS);
		Frame secondNext = createFrame(SECOND_FRAME_PINS);

		frame.setNext(firstNext);

		when(firstNext.hasNext()).thenReturn(Boolean.TRUE);
		when(firstNext.getNext()).thenReturn(secondNext);

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
