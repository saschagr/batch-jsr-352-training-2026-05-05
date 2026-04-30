package de.mathema.batch.hello.partition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.batch.api.partition.AbstractPartitionAnalyzer;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class HelloPartitionAnalyzer extends AbstractPartitionAnalyzer {

    @Inject
    private StepContext sCtx;

    @Override
    public void analyzeCollectorData(Serializable data) throws Exception {
        System.out.println("Analyzing...\n" + data);
        if(sCtx.getPersistentUserData() == null){
			sCtx.setPersistentUserData(new ArrayList<String>());
		}

		((List<String>)sCtx.getPersistentUserData()).addAll(((List<String>)data).stream().map(o -> o.toString()).toList());
    } 
}
