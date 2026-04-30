package de.mathema.batch.job.defineMembershipFlow;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.chunk.ItemProcessor;

public class DefineMembershipProcessor implements ItemProcessor {
  @Override
  public Object processItem(Object item) {
    Customer currentCustomer = (Customer) item;
    // ToDo: Calculate and set Membership
    return currentCustomer;
  }

}
