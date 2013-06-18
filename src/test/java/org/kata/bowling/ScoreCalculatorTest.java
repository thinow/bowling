package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScoreCalculatorTest {

	private static final String GAME = "any-game-line";

	private static final GameEntry ENTRY = mock(GameEntry.class);
	private static final GameEntry ANOTHER_ENTRY = mock(GameEntry.class);

	private static final Frame ANY_FRAME = createFrame(1);

	private static final int SCORE = 5;
	private static final int ANOTHER_SCORE = 8;

	@Mock
	private Parser parser;
	@Mock
	private FrameFactory frameFactory;

	@Test
	public void calculatorParseAndCreateFrames() throws Exception {
		// given
		when(parser.parseGame(anyString())).thenReturn(newArrayList(ENTRY, ANOTHER_ENTRY));
		when(frameFactory.createFrame(any(GameEntry.class))).thenReturn(ANY_FRAME);

		// when
		calculateGameScore(GAME);

		// then
		verify(parser).parseGame(GAME);
		verify(frameFactory).createFrame(ENTRY);
		verify(frameFactory).createFrame(ANOTHER_ENTRY);
	}

	@Test
	public void onlyOnceFrameGame() throws Exception {
		// given
		defineGameEntries(ENTRY);

		defineFrameForEntry(ENTRY, createFrame(SCORE));

		// when
		int score = calculateGameScore(GAME);

		// then
		assertThat(score).isEqualTo(SCORE);
	}

	@Test
	public void severalFrames() throws Exception {
		// given
		defineGameEntries(ENTRY, ANOTHER_ENTRY);

		defineFrameForEntry(ENTRY, createFrame(SCORE));
		defineFrameForEntry(ANOTHER_ENTRY, createFrame(ANOTHER_SCORE));

		// when
		int score = calculateGameScore(GAME);

		// then
		int sum = SCORE + ANOTHER_SCORE;
		assertThat(score).isEqualTo(sum);
	}

	private void defineGameEntries(GameEntry... entries) {
		when(parser.parseGame(anyString())).thenReturn(newArrayList(entries));
	}

	private void defineFrameForEntry(GameEntry entry, Frame frame) {
		when(frameFactory.createFrame(entry)).thenReturn(frame);
	}

	private static Frame createFrame(int score) {
		Frame frame = mock(Frame.class);
		when(frame.getScore()).thenReturn(score);

		return frame;
	}

	private int calculateGameScore(String game) {
		ScoreCalculator calculator = new ScoreCalculator(parser, frameFactory);
		return calculator.calculate(game);
	}
}
