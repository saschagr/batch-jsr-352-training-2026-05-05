package de.mathema.batch.split.batchlet;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.context.JobContext;
import jakarta.inject.Inject;

public class CollectResultsBatchlet extends AbstractBatchlet {

    @Inject JobContext _jctxt;

    @Override
    public String process() throws Exception {
        WorkingData result = WorkingData.INSTANCE;
        System.out.println("FastResult: " + result.fastResult);
        System.out.println("MediumResult: " + result.mediumResult);
        System.out.println("SlowResult: " + result.slowResult);
        return "OK";
    }
    
}
