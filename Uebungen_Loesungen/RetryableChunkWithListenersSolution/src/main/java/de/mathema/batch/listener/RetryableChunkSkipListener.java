package de.mathema.batch.listener;

import java.util.Arrays;

import jakarta.batch.api.chunk.listener.SkipProcessListener;

public class RetryableChunkSkipListener implements SkipProcessListener {

    @Override
    public void onSkipProcessItem(Object item, Exception ex) throws Exception {
        System.out.println("Skipping after " + ex.getClass().getSimpleName() + " on '" + Arrays.toString((String[])item) + "'");
    }
    
}
