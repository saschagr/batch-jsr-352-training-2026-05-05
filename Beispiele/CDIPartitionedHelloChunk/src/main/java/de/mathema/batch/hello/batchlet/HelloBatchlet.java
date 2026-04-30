package de.mathema.batch.hello.batchlet;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.context.JobContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class HelloBatchlet extends AbstractBatchlet {

    @Inject
    private JobContext jCtx;

    @Override
    public String process() throws Exception {
        if(jCtx.getTransientUserData() == null ) {
            System.out.println("No data found! :-(");
            return "NOK";
        } else {
            System.out.println("Data found! :-)");
            System.out.println(jCtx.getTransientUserData() );
            return "OK";
        }
    }
}