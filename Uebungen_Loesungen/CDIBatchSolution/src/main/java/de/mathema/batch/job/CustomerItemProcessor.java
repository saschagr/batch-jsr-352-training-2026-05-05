package de.mathema.batch.job;

import java.util.Arrays;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.inject.Named;

//@RequestScoped
@Named
public class CustomerItemProcessor implements ItemProcessor {

  @Override
  public Object processItem(Object item) {
    String[] data = (String[]) item;
    System.out.println("processItem: " + Arrays.toString(data));

    return new Customer(data[0], data[1], data[2], data[3]);
  }
}
