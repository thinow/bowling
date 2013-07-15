package org.kata.bowling;

import java.util.Collection;

public interface Parser<T> {

	Collection<GameEntry> parse(T game);

}