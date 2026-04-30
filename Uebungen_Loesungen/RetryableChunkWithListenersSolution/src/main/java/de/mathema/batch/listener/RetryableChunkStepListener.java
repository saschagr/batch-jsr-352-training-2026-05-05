package de.mathema.batch.listener;

import jakarta.batch.api.listener.StepListener;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class RetryableChunkStepListener implements StepListener {

    @Inject
    private StepContext sCtx;

    private long startTime;

    @Override
    public void beforeStep() throws Exception {
        startTime = System.currentTimeMillis();
        System.out.println("Starting step '" + sCtx.getStepName() + "'...");
    }

    @Override
    public void afterStep() throws Exception {
        long endTime = System.currentTimeMillis();
        System.out.println("Step ended after " + (endTime - startTime) + "ms with ExitStatus: " + sCtx.getExitStatus());
    }
    
}
