package de.mathema.batch.job.calculateDiscountPercentageFlow;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.chunk.ItemProcessor;

public class DBSetDiscountItemProcessor implements ItemProcessor {
  public static final String PREMIUM_DISCOUNT_PERCENTAGE = "20";
  public static final String BASIC_DISCOUNT_PERCENTAGE = "10";

  @Override
  public Object processItem(Object item) {
    Customer currentCustomer = (Customer) item;
    decideDiscountPercentageForCustomer(currentCustomer);
    return currentCustomer;
  }

  private static void decideDiscountPercentageForCustomer(Customer currentCustomer) {
    if ("premium".equalsIgnoreCase(currentCustomer.getMembership())) {
      currentCustomer.setDiscountPercentage(PREMIUM_DISCOUNT_PERCENTAGE);
    } else if ("basic".equalsIgnoreCase(currentCustomer.getMembership())) {
      currentCustomer.setDiscountPercentage(BASIC_DISCOUNT_PERCENTAGE);
    }
  }

}
