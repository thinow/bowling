package org.kata.bowling;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Frame {

	@AllArgsConstructor
	public static class Try {
		@Getter
		private int pins;
	}

	Collection<Try> asTries();

	int getScore();

}
