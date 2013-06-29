package org.kata.bowling;

import static java.lang.String.*;

public final class Logger {

	private static final Logger INSTANCE = new Logger();

	public static Logger getInstance() {
		return INSTANCE;
	}

	private Logger() {
	}

	public void printNewGame(String game) {
		info("--------------------");
		info(format("Game : %s", game));
	}

	public void printFrame(Frame frame) {
		String type = frame.getClass().getSimpleName();
		info("> %s\t: score %d", type, frame.getScore());
	}

	private void info(String message, Object... arguments) {
		String text = format(message, arguments);
		System.out.println(text);
	}
}
