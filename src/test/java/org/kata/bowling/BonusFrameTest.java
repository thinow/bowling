package org.kata.bowling;

import static org.fest.assertions.Assertions.*;

import java.util.Collection;

import org.junit.Test;
import org.kata.bowling.Frame.Try;

public class BonusFrameTest {

	private static final int PINS = 8;

	@Test
	public void scoreIsKnockedPinsCount() throws Exception {
		// given
		BonusFrame frame = new BonusFrame(PINS);

		// when
		int score = frame.getScore();

		// then
		assertThat(score).isZero();
	}

	@Test
	public void bonusAsOnlyOneTry() throws Exception {
		// given
		BonusFrame frame = new BonusFrame(PINS);

		// when
		Collection<Try> tries = frame.asTries();

		// then
		assertThat(tries).hasSize(1);
		assertThat(tries).onProperty("pins").containsOnly(PINS);
	}

}
