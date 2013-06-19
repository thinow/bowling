package org.kata.bowling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class GameEntry {

	public static enum Type {
		STRIKE, SPARE, FAILED
	}

	@NonNull
	private Type type;
	private int firstTry;
	private int secondTry;

	public int getPins() {
		return firstTry + secondTry;
	}

}
