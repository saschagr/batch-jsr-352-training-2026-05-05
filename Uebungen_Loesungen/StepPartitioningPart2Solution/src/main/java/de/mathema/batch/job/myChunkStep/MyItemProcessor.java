package de.mathema.batch.job.myChunkStep;

import java.util.HashMap;

import de.mathema.batch.job.Customer;
import de.mathema.batch.util.Constants;
import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class MyItemProcessor implements ItemProcessor {

	@Inject
	StepContext stepContext;

	int customerCounter = 0;

	HashMap<String, Customer> collectedData = new HashMap<>();

	@Override
	public Object processItem(Object item) throws Exception {
		String[] data = (String[])item;
		Customer customer = new Customer(data[0], data[1], data[2], data[3]);
		if(customer.getCity().equals(Constants.MÜNCHEN)) {
			customerCounter++;
			collectedData.put(customer.getCustomerId(), customer);
		}
		stepContext.setPersistentUserData(collectedData);
		return customer;
	}
}
