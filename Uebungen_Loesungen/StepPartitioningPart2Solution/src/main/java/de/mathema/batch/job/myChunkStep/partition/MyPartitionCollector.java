package de.mathema.batch.job.myChunkStep.partition;

import java.io.Serializable;
import java.util.HashMap;

import jakarta.batch.api.partition.PartitionCollector;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;


public class MyPartitionCollector implements PartitionCollector {
    @Inject
    StepContext stepContext;

    int collectorCounter = 0;
    @Override
    public Serializable collectPartitionData() throws Exception {
        collectorCounter++;
        System.out.println("PartitionCollector.collectPartitionData(), " + "CollectorCounter = " + collectorCounter);
        return new HashMap<>((HashMap)stepContext.getPersistentUserData());
    }
}
