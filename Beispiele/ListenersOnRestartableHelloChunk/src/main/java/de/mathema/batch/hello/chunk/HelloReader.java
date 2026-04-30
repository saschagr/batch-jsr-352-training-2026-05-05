package de.mathema.batch.hello.chunk;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import jakarta.batch.api.chunk.AbstractItemReader;


public class HelloReader extends AbstractItemReader {

	public static class HelloCheckpoint implements Serializable{
		private int lastItemProcessed = -1;
	}

	private final static List<String> NAMES = List.of("Ella","Frank","Patricia","Victor","World");
	private Iterator<String> nameIterator;
	private HelloCheckpoint helloCheckpoint;
	
	@Override
	public void open(Serializable checkpoint) {
		helloCheckpoint = checkpoint!=null?(HelloCheckpoint) checkpoint:new HelloCheckpoint();
		
		System.out.println("Checkpoint: " + helloCheckpoint.lastItemProcessed);

		if((helloCheckpoint).lastItemProcessed > -1) {
			nameIterator = NAMES.stream().skip(helloCheckpoint.lastItemProcessed+1).iterator();
		} else {
			nameIterator = NAMES.iterator();
		} 		
	}
	
	@Override
	public Object readItem() {
		if(Math.random() < 0.34) { 
			System.out.println("*** BOOM!!! ***");
			throw new IllegalArgumentException("Upps! ;-)"); 
		}
		if(nameIterator!= null && nameIterator.hasNext()) {
			String item = nameIterator.next();
			helloCheckpoint.lastItemProcessed++;
			return item;
		} else {
			return null;
		} 		
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		System.out.println("Returning Checkpoint: " + helloCheckpoint.lastItemProcessed);
		return helloCheckpoint;
	}

}
