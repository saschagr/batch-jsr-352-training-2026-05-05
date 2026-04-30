package de.mathema.batch.split.batchlet;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.context.JobContext;
import jakarta.inject.Inject;

public class PrepareUserDataBatchlet extends AbstractBatchlet {

    @Inject JobContext _jctxt;

    @Override
    public String process() {
        WorkingData.reset();
        return "OK";
    }
    
}
