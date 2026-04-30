package de.mathema.batch.hello.listener;

import jakarta.batch.api.listener.JobListener;
import jakarta.batch.runtime.context.JobContext;
import jakarta.inject.Inject;

public class HelloJobListener implements JobListener {

    @Inject
    private JobContext jCtx;

    private long startTime;

    @Override
    public void beforeJob() {
        startTime = System.currentTimeMillis();
        System.out.println("Batch Job '" + jCtx.getJobName() + "'' is starting...");
    }

    @Override
    public void afterJob() {
        long endTime = System.currentTimeMillis();
        System.out.println("Batch job ended after " + (endTime - startTime) + "ms.");
    }
    
}
