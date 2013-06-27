package org.kata.bowling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class Frame {

	@Getter
	private int knockedPins;

	abstract int getScore();

}
