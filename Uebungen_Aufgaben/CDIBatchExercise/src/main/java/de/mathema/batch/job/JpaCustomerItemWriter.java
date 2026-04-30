package de.mathema.batch.job;

import java.util.List;

import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class JpaCustomerItemWriter extends AbstractItemWriter {

  @Override
  public void writeItems(List<Object> items) throws Exception {

  }
}
