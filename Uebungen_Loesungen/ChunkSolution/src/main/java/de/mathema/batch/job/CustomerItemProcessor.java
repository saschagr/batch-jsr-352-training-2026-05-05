package de.mathema.batch.job;

import jakarta.batch.api.chunk.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor {

  @Override
  public Object processItem(Object item) throws Exception {
    String[] data = (String[])item;
    return new Customer(data[0], data[1], data[2], data[3]);
  }
}
