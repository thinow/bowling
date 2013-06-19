package org.kata.bowling;

import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.GameEntry.Type.*;

import java.util.Collection;

import org.junit.Test;
import org.kata.bowling.GameEntry.Type;

import com.google.common.base.Function;

public class FrameFactoryTest {

	private static final int ALL_PINS = 10;

	protected static final int FIRST_TRY = 2;
	protected static final int SECOND_TRY = 5;

	private FrameFactory factory = new FrameFactory();

	@Test
	public void createFailedFrame() throws Exception {
		// when
		Frame frame = createFrame(FAILED);

		// then
		assertExpectedFrame(frame, FailedFrame.class, FIRST_TRY + SECOND_TRY);
	}

	@Test
	public void createSpareFrame() throws Exception {
		// when
		Frame frame = createFrame(SPARE);

		// then
		assertExpectedFrame(frame, SpareFrame.class, ALL_PINS);
	}

	@Test
	public void createStrikeFrame() throws Exception {
		// when
		Frame frame = createFrame(STRIKE);

		// then
		assertExpectedFrame(frame, StrikeFrame.class, ALL_PINS);
	}

	private Frame createFrame(Type type) {
		Collection<Frame> frames = createFrames(type);
		return getOnlyElement(frames);
	}

	private Collection<Frame> createFrames(Type... types) {
		Collection<GameEntry> entries = createEntries(types);
		return factory.createFrames(entries);
	}

	private Collection<GameEntry> createEntries(Type... types) {
		return transform(copyOf(types), new Function<Type, GameEntry>() {
			@Override
			public GameEntry apply(Type type) {
				return new GameEntry(type, FIRST_TRY, SECOND_TRY);
			}
		});
	}

	private void assertExpectedFrame(Frame frame, Class<? extends Frame> type, int pins) {
		assertThat(frame).isInstanceOf(type);
		assertThat(frame.getKnockedPins()).isEqualTo(pins);
	}

}
