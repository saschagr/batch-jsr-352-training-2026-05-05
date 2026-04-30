package de.mathema.batch.hello.listener;

import jakarta.batch.api.chunk.listener.RetryReadListener;

public class HelloRetryListener implements RetryReadListener{

    @Override
    public void onRetryReadException(Exception ex) throws Exception {
        System.out.println("Retrying after " + ex.getClass().getSimpleName());
    }
    
}
