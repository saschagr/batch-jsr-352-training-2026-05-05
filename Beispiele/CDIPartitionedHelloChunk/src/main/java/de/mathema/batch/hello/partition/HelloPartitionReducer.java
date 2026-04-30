package de.mathema.batch.hello.partition;

import jakarta.batch.api.partition.AbstractPartitionReducer;
import jakarta.batch.runtime.context.JobContext;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class HelloPartitionReducer extends AbstractPartitionReducer {

    @Inject
    private StepContext sCtx;

    @Inject
    private JobContext jCtx;

    @Override
    public void afterPartitionedStepCompletion(PartitionStatus status) {
        System.out.println("Step completed: " + status);
    }

    @Override
    public void beforePartitionedStepCompletion() {
        System.out.println("Making result globally available...");
        jCtx.setTransientUserData(sCtx.getPersistentUserData());
    }

    @Override
    public void beginPartitionedStep() {
         System.out.println("Let's start the partition...");
    }

}
