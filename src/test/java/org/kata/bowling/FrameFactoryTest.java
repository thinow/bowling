package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.GameEntry.Type.*;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kata.bowling.GameEntry.Type;

public class FrameFactoryTest {

	private static final Frame LAST = mock(Frame.class);
	private static final Frame SECOND_TO_LAST = mock(Frame.class);
	private static final Frame ANOTHER_FRAME = mock(Frame.class);

	private static final Collection<Frame> PREVIOUS_FRAMES = newArrayList(ANOTHER_FRAME,
			SECOND_TO_LAST, LAST);

	private static final int PINS = 2;
	private static final int ALL_PINS = 10;

	private FrameFactory factory = new FrameFactory();

	@Test
	public void createFailedFrame() throws Exception {
		// when
		Frame frame = createFrame(FAILED);

		// then
		assertExpectedFrame(frame, FailedFrame.class, PINS);
	}

	@Test
	public void createSpareFrame() throws Exception {
		// when
		Frame frame = createFrame(SPARE);

		// then
		assertExpectedFrame(frame, SpareFrame.class, PINS);
	}

	@Test
	public void createStrikeFrame() throws Exception {
		// when
		Frame frame = createFrame(STRIKE);

		// then
		assertExpectedFrame(frame, StrikeFrame.class, ALL_PINS);
	}

	private Frame createFrame(Type type) {
		GameEntry entry = new GameEntry(type, PINS, 0);
		return factory.createFrame(entry, PREVIOUS_FRAMES);
	}

	private void assertExpectedFrame(Frame frame, Class<? extends Frame> type, int pins) {
		assertThat(frame).isInstanceOf(type);
		assertThat(frame.getKnockedPins()).isEqualTo(pins);

		verify(LAST).setNext(frame);
	}

	@Test
	public void tryToLinkFramesWithoutPrevious() throws Exception {
		// given
		Frame frame = mock(Frame.class);

		// when
		factory.linkFrames(noFrames(), frame);

		// then
		verifyNoMoreInteractions(frame);
	}

	private List<Frame> noFrames() {
		return Collections.<Frame> emptyList();
	}

}
