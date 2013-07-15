package org.kata.bowling;

import static java.lang.String.*;

import org.kata.bowling.frame.Frame;

public final class Logger {

	private static final Logger INSTANCE = new Logger();

	public static Logger getInstance() {
		return INSTANCE;
	}

	private Logger() {
	}

	public void printNewGame(Object game) {
		println("--------------------");
		println(format("Game : %s", game));
	}

	public void printFrame(Frame frame) {
		String type = frame.getClass().getSimpleName();
		print("> %s(%d)", type, frame.getScore());
	}

	public void printIntermediateScore(int score) {
		println("\tscore %d", score);
	}

	public void printFinalScore(int score) {
		println("Final score = %s", score);
	}

	private void print(String message, Object... arguments) {
		System.out.print(format(message, arguments));
	}

	private void println(String message, Object... arguments) {
		System.out.println(format(message, arguments));
	}

}
