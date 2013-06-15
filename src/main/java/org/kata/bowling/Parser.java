package org.kata.bowling;

import static com.google.common.collect.Lists.*;

import java.util.Collection;

public class Parser {

	public Collection<Frame> parse(String game) {
		Collection<Frame> frames = newArrayList();
		frames.add(new StrikeFrame());

		return frames;
	}

}
