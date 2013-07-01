package org.kata.bowling;

import static com.google.common.collect.Collections2.*;
import static com.google.common.collect.ImmutableList.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.kata.bowling.Frame.Try;

import com.google.common.base.Function;

public class MockedFrameHelper {

	public static Frame createFrame(Integer... tries) {
		Frame frame = mock(Frame.class);
		when(frame.asTries()).thenReturn(transformAsObjects(tries));

		return frame;
	}

	private static Collection<Try> transformAsObjects(Integer... tries) {
		return transform(copyOf(tries), new Function<Integer, Try>() {
			@Override
			public Try apply(Integer pins) {
				return new Try(pins);
			}
		});
	}

}
