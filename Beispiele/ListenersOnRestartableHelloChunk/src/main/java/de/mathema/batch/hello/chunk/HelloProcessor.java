package de.mathema.batch.hello.chunk;

import jakarta.batch.api.chunk.ItemProcessor;

public class HelloProcessor implements ItemProcessor {
	
	@Override
	public Object processItem(Object item) {
		System.out.println("Processing " + item + "...");
		if("Frank".equals(item)) {
			// throw new UnsupportedOperationException("Cannot process a Frank!");
		}
		return String.format("Hello %s!", item);
	}

}
