package de.mathema.batch.job.calculateDiscountPercentageFlow;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.chunk.ItemProcessor;

public class DBCalculateExtraItemProcessor implements ItemProcessor {

  private static final int EXTRA_DISCOUNT = 20;

  @Override
  public Object processItem(Object item) {
    Customer currentCustomer = (Customer) item;

    if ("München".equalsIgnoreCase(currentCustomer.getCity())) {
      calculateNewDiscountPercentage(currentCustomer);
    }
    return currentCustomer;
  }

  private static void calculateNewDiscountPercentage(Customer currentCustomer) {
    int discountPercentage = Integer.valueOf(currentCustomer.getDiscountPercentage());
    String newDiscount = String.valueOf(discountPercentage + EXTRA_DISCOUNT);
    currentCustomer.setDiscountPercentage(newDiscount);
  }

}
