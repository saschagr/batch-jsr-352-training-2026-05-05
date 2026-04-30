package de.mathema.batch.job.initializeFlow;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.chunk.ItemProcessor;

public class MyItemProcessor implements ItemProcessor {

  @Override
  public Object processItem(Object item) {
    String[] data = (String[])item;
    return new Customer(data[0], data[1], data[2], data[3], data[4]);
  }
}
