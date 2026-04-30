package de.mathema.batch.hello.chunk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
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
		List<String> contextData = (List<String>) sCtx.getPersistentUserData();
		contextData.addAll(items.stream().map(o -> o.toString()).toList());
		sCtx.setPersistentUserData((Serializable)contextData);
	}
}
