package de.mathema.batch.hello.chunk;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.inject.Named;

@Named
public class HelloProcessor implements ItemProcessor {
	
	@Override
	public Object processItem(Object item) {
		System.out.println("Processing " + item + "...");
		return String.format("Hello %s!", item);
	}

}
