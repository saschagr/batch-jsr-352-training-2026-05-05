package de.mathema.batch.hello.chunk;

import java.util.ArrayList;
import java.util.List;

import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class HelloWriter extends AbstractItemWriter {

	@Inject
    private StepContext sCtx;

	@SuppressWarnings("unchecked")
	@Override
	public void writeItems(List<Object> items) {
		if(sCtx.getPersistentUserData() == null){
			sCtx.setPersistentUserData(new ArrayList<String>());
		}
		items.forEach(System.out::println);
		((List<String>)sCtx.getPersistentUserData()).addAll(items.stream().map(o -> o.toString()).toList());
	}
}
