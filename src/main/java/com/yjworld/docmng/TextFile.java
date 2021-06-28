package com.yjworld.docmng;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static com.yjworld.docmng.Attributes.*;

class TextFile {
    private final Map<String, String> attributes;
	private final List<String> lines;

	TextFile(final File file) throws IOException {
		attributes = new HashMap<>();
		attributes.put(PATH, file.getPath());
		lines = Files.lines(file.toPath()).collect(toList());
	}

	int addLines(
			final int start,
			final Predicate<String> isEnd,
			final String attributeName) {
		final StringBuilder accumulator = new StringBuilder();
		int lineNumber;

		for (lineNumber = start; lineNumber < lines.size(); lineNumber++) {
			final String line = lines.get(lineNumber);

			if (isEnd.test(line)) {
				break;
			}
			accumulator.append(line);
			accumulator.append("\n");
		}
		attributes.put(attributeName, accumulator.toString().trim());
		return lineNumber;
	}
}
