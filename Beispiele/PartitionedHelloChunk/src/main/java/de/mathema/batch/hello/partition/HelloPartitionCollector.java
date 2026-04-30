package de.mathema.batch.hello.partition;

import java.io.Serializable;

import jakarta.batch.api.partition.PartitionCollector;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class HelloPartitionCollector implements PartitionCollector {

    @Inject
    private StepContext sCtx;

    @Override
    public Serializable collectPartitionData() throws Exception {
        System.out.println("Collecting...\n" + sCtx.getPersistentUserData());
        return sCtx.getPersistentUserData();
    }
    
}
