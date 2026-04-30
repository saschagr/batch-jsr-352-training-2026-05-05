package de.mathema.batch.hello.chunk;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import jakarta.batch.api.chunk.AbstractItemReader;

public class HelloReader extends AbstractItemReader {
	private final static List<String> NAMES = List.of("Ella","Frank","Patricia","Victor","World");
	private Iterator<String> nameIterator;
	
	@Override
	public void open(Serializable checkpoint) {
		nameIterator = NAMES.iterator();
	}
	
	@Override
	public Object readItem() {
		if(nameIterator!= null && nameIterator.hasNext()) {
			return nameIterator.next();
		} else {
			return null;
		} 		
	}
}
