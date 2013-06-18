package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScoreCalculatorMockedTest {

	private static final String GAME = "any-game-line";
	private static final Frame ANY_FRAME = createFrame(1);

	@Mock
	private Parser parser;
	@Mock
	private FrameFactory frameFactory;

	@Test
	public void calculatorParseAndCreateFrames() throws Exception {
		// given
		GameEntry entry = mock(GameEntry.class);
		GameEntry anotherEntry = mock(GameEntry.class);

		when(parser.parseGame(anyString())).thenReturn(newArrayList(entry, anotherEntry));
		when(frameFactory.createFrame(any(GameEntry.class))).thenReturn(ANY_FRAME);

		// when
		calculateGameScore(GAME);

		// then
		verify(parser).parse(GAME);
		verify(frameFactory).createFrame(entry);
		verify(frameFactory).createFrame(anotherEntry);
	}

	@Test
	public void onlyOnceFrameGame() throws Exception {
		// given
		Frame frame = createFrame(5);
		defineGameFrames(GAME, newArrayList(frame));

		// when
		int score = calculateGameScore(GAME);

		// then
		assertThat(score).isEqualTo(frame.getScore());
	}

	@Test
	public void severalFrames() throws Exception {
		// given
		Frame frame = createFrame(1);
		Frame anotherFrame = createFrame(2);
		defineGameFrames(GAME, newArrayList(frame, anotherFrame));

		// when
		int score = calculateGameScore(GAME);

		// then
		int sum = frame.getScore() + anotherFrame.getScore();
		assertThat(score).isEqualTo(sum);
	}

	private static Frame createFrame(int score) {
		Frame frame = mock(Frame.class);
		when(frame.getScore()).thenReturn(score);

		return frame;
	}

	private void defineGameFrames(String gameLine, Collection<Frame> frames) {
		when(parser.parse(gameLine)).thenReturn(frames);
	}

	private int calculateGameScore(String game) {
		ScoreCalculator calculator = new ScoreCalculator(parser, frameFactory);
		return calculator.calculate(game);
	}
}
