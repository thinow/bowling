package org.kata.bowling;

import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.GameEntry.Type.*;

import org.junit.Test;

public class FrameFactoryTest {

	private static final int PINS = 2;

	private FrameFactory factory = new FrameFactory();

	@Test
	public void createFailedFrame() throws Exception {
		// given
		GameEntry entry = new GameEntry(FAILED, PINS);

		// when
		Frame frame = factory.createFrame(entry);

		// then
		assertThat(frame).isInstanceOf(FailedFrame.class);
		assertThat(frame.getKnockedPins()).isEqualTo(PINS);

		// TODO Gérer la frame suivante
		// assertThat(frame.getNext()).isEqualTo(???);
	}

}
