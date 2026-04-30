package de.mathema.batch.listener;

import jakarta.batch.api.chunk.listener.RetryReadListener;

public class RetryableChunkRetryListener implements RetryReadListener{

    @Override
    public void onRetryReadException(Exception ex) throws Exception {
        System.out.println("Retrying after " + ex.getClass().getSimpleName());
    }
    
}
