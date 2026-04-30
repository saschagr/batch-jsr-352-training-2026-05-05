package de.mathema.batch.job;

import jakarta.batch.api.chunk.CheckpointAlgorithm;

public class SimpleCheckpointAlgorithm implements CheckpointAlgorithm {

    private int numOfRounds = 0;

    @Override
    public int checkpointTimeout() throws Exception {
        return 0;
    }

    @Override
    public void beginCheckpoint() {
    }

    @Override
    public boolean isReadyToCheckpoint() throws Exception {
        numOfRounds++;
        return numOfRounds%10==0;
    }

    @Override
    public void endCheckpoint() throws Exception {
    }

}
