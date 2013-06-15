package org.kata.bowling;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScoreCalculatorMockedTest {

	private static final String GAME = "any-game-line";

	@Mock
	private Parser parser;

	@Test
	public void onlyOnceFrameGame() throws Exception {
		// given
		Frame frame = createFrame(5);
		defineGameFrames(GAME, newArrayList(frame));

		// when
		int score = calculateGameScore(GAME);

		// then
		assertThat(score).isEqualTo(5);
	}

	private Frame createFrame(int score) {
		Frame frame = mock(Frame.class);
		when(frame.getScore()).thenReturn(score);

		return frame;
	}

	private void defineGameFrames(String gameLine, Collection<Frame> frames) {
		when(parser.parse(gameLine)).thenReturn(frames);
	}

	private int calculateGameScore(String game) {
		ScoreCalculator calculator = new ScoreCalculator(parser);
		return calculator.calculate(game);
	}
}
