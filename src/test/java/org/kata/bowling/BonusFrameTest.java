package org.kata.bowling;

import static org.fest.assertions.Assertions.*;

import org.junit.Test;

public class BonusFrameTest {

	private static final int PINS = 8;

	@Test
	public void scoreIsKnockedPinsCount() throws Exception {
		// given
		BonusFrame frame = new BonusFrame(PINS);

		// when
		int score = frame.getScore();

		// then
		assertThat(score).isEqualTo(PINS);
	}

}
