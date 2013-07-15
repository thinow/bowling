package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kata.bowling.frame.Frame;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScoreCalculatorTest {

	private static final Object GAME = "any-game-input";

	private static final Collection<GameEntry> ENTRIES = newArrayList(mock(GameEntry.class));

	private static final int SCORE = 5;
	private static final int ANOTHER_SCORE = 8;

	@Mock
	private Parser<Object> parser;
	@Mock
	private FrameFactory frameFactory;

	@Test
	public void calculatorSumFramesScores() throws Exception {
		// given
		when(parser.parse(anyString())).thenReturn(ENTRIES);

		Frame first = createFrame(SCORE);
		Frame second = createFrame(ANOTHER_SCORE);
		when(frameFactory.createFrames(anyEntries())).thenReturn(newArrayList(first, second));

		// when
		int score = calculateGameScore(GAME);

		// then
		verify(parser).parse(GAME);
		verify(frameFactory).createFrames(ENTRIES);

		assertThat(score).isEqualTo(SCORE + ANOTHER_SCORE);
	}

	private static Frame createFrame(int score) {
		Frame frame = mock(Frame.class);
		when(frame.getScore()).thenReturn(score);

		return frame;
	}

	private Collection<GameEntry> anyEntries() {
		return anyCollectionOf(GameEntry.class);
	}

	private int calculateGameScore(Object game) {
		ScoreCalculator<Object> calculator = new ScoreCalculator<>(parser, frameFactory);
		return calculator.calculate(game);
	}
}
