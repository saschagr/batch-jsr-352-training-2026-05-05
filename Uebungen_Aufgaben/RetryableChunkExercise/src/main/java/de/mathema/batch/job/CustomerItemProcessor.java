package de.mathema.batch.job;

import java.util.Arrays;

import jakarta.batch.api.chunk.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor {

  @Override
  public Object processItem(Object item) throws Exception {
    String[] data = (String[])item;
    System.out.println("Process: " + Arrays.toString(data));
    return new Customer(data[0], data[1], data[2], data[3]);
  }
  
}
