package org.kata.bowling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameEntry {

	public static enum Type {
		STRIKE, SPARE, FAILED, BONUS
	}

	private Type type;
	private int pins;

}
