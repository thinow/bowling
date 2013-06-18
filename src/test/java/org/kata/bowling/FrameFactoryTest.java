package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.GameEntry.Type.*;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class FrameFactoryTest {

	private static final Frame LAST = mock(Frame.class);
	private static final Frame SECOND_TO_LAST = mock(Frame.class);
	private static final Frame ANOTHER_FRAME = mock(Frame.class);

	private static final Collection<Frame> PREVIOUS_FRAMES = newArrayList(ANOTHER_FRAME,
			SECOND_TO_LAST, LAST);

	private static final int PINS = 2;

	private FrameFactory factory = new FrameFactory();

	@Test
	public void createFailedFrame() throws Exception {
		// given
		GameEntry entry = new GameEntry(FAILED, PINS);

		// when
		Frame frame = factory.createFrame(entry, PREVIOUS_FRAMES);

		// then
		assertThat(frame).isInstanceOf(FailedFrame.class);
		assertThat(frame.getKnockedPins()).isEqualTo(PINS);

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
