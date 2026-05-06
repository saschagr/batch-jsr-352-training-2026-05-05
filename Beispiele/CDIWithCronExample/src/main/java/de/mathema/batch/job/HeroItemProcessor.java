package de.mathema.batch.job;

import java.util.Arrays;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class HeroItemProcessor implements ItemProcessor {

  @Override
  public Object processItem(Object item) {
    String[] data = (String[]) item;
    System.out.println("processItem: " + Arrays.toString(data));

    return new Hero(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
  }
}
