package org.kata.bowling;

import static org.fest.assertions.Assertions.*;

import org.fest.assertions.IntAssert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ComponentTest {

	private ScoreCalculator calculator;

	@Before
	public void setUp() throws Exception {
		Parser parser = new Parser();
		FrameFactory frameFactory = new FrameFactory();
		calculator = new ScoreCalculator(parser, frameFactory);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresOnceAndMiss() throws Exception {
		assertThatScoreOf("4-").isEqualTo(4);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresMorePinsAndMiss() throws Exception {
		assertThatScoreOf("9-").isEqualTo(9);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresTwice() throws Exception {
		assertThatScoreOf("42").isEqualTo(6);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresWithStrikesOnly() throws Exception {
		assertThatScoreOf("XXXXXXXXXXXX").isEqualTo(300);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresWithSparesOnly() throws Exception {
		assertThatScoreOf("5/5/5/5/5/5/5/5/5/5/5").isEqualTo(150);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresWithMisses() throws Exception {
		assertThatScoreOf("9-9-9-9-9-9-9-9-9-9-").isEqualTo(90);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresSample1() throws Exception {
		assertThatScoreOf("7-1/3672X7/-87-41-8").isEqualTo(96);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresSample2() throws Exception {
		assertThatScoreOf("9/XX7/X81818-33X9/").isEqualTo(158);
	}

	@Test
	@Ignore("Test ignoré durant le refactoring du parser")
	public void scoresSample3() throws Exception {
		assertThatScoreOf("63718/72X627/X8-7/").isEqualTo(135);
	}

	private IntAssert assertThatScoreOf(String line) {
		int score = calculator.calculate(line);
		return assertThat(score);
	}

}
