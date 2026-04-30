package de.mathema.batch.hello.chunk;

import java.util.List;

import jakarta.batch.api.chunk.AbstractItemWriter;

public class HelloWriter extends AbstractItemWriter {

	@Override
	public void writeItems(List<Object> items) {
		items.forEach(System.out::println);
	}
}
