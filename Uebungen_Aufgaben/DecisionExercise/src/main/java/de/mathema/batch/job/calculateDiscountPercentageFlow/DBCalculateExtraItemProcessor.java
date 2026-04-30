package de.mathema.batch.job.calculateDiscountPercentageFlow;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.chunk.ItemProcessor;

public class DBCalculateExtraItemProcessor implements ItemProcessor {

  private static final int EXTRA_DISCOUNT = 20;

  @Override
  public Object processItem(Object item) {
    Customer currentCustomer = (Customer) item;

    return currentCustomer;
  }
}
