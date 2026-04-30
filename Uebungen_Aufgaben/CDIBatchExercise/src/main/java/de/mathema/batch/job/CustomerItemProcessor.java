package de.mathema.batch.job;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class CustomerItemProcessor implements ItemProcessor {

  @Override
  public Object processItem(Object item) {

    return item;
  }
}
