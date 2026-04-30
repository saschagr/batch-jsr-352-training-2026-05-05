package de.mathema.batch.hello.chunk;

import java.io.Serializable;
import java.util.Iterator;

import de.mathema.batch.hello.HelloData;
import jakarta.batch.api.BatchProperty;
import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.inject.Inject;

public class HelloPartitionedReader extends AbstractItemReader {
	
	@Inject
    @BatchProperty
    private int firstItem;
    
	@Inject
    @BatchProperty
    private int lastItem;

	private Iterator<String> nameIterator;
	
	@Override
	public void open(Serializable checkpoint) {
		System.out.println(this + "Opening... (" + firstItem + "-" + lastItem + ")");
		nameIterator = HelloData.NAMES.stream().skip(firstItem).limit(lastItem-firstItem+1).iterator();
	}
	
	@Override
	public Object readItem() throws InterruptedException {
		if(nameIterator!= null && nameIterator.hasNext()) {
			System.out.println(this + "Reading...");
			return nameIterator.next();
		} else {
			System.out.println(this + "EOF");
			return null;
		} 		
	}
}
