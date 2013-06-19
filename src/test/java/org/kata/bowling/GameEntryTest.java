package org.kata.bowling;

import static org.fest.assertions.Assertions.*;
import static org.kata.bowling.GameEntry.Type.*;

import org.junit.Test;
import org.kata.bowling.GameEntry.Type;

public class GameEntryTest {

	private static final int FIRST_TRY = 2;
	private static final int SECOND_TRY = 3;

	private static final Type NO_TYPE = null;

	@Test
	public void sumTriesToComputeTotal() throws Exception {
		// given
		GameEntry entry = new GameEntry(STRIKE, FIRST_TRY, SECOND_TRY);

		// when / then
		assertThat(entry.getPins()).isEqualTo(FIRST_TRY + SECOND_TRY);
	}

	@Test(expected = NullPointerException.class)
	public void cannotCreateEntryWithoutType() throws Exception {
		new GameEntry(NO_TYPE, FIRST_TRY, SECOND_TRY);
	}

}
