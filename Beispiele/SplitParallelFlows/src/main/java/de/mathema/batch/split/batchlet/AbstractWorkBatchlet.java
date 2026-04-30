package de.mathema.batch.split.batchlet;

import jakarta.batch.api.AbstractBatchlet;

public abstract class AbstractWorkBatchlet extends AbstractBatchlet {

    private final int iterations;
    private final int sleepTimeInMillis;

    protected AbstractWorkBatchlet(int iterations, int sleepTimeInMillis){
        this.iterations = iterations;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public String process() throws Exception {
        final String name = this.getClass().getSimpleName();
        System.out.println(name + " is starting...");
        for(int i = 0; i< iterations;i++){
          Thread.sleep(sleepTimeInMillis);
          System.out.println(name + " is working...");
        }

        System.out.println(name + " has finished!");
        return "OK";
    }
  
}
