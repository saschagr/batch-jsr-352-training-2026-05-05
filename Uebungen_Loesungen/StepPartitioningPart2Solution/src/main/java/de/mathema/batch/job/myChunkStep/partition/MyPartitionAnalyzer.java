package de.mathema.batch.job.myChunkStep.partition;

import java.io.Serializable;
import java.util.HashMap;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.partition.PartitionAnalyzer;
import jakarta.batch.runtime.BatchStatus;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class MyPartitionAnalyzer implements PartitionAnalyzer {
    HashMap<Integer, Customer> collection = new HashMap<>();

    @Inject
    StepContext stepContext;

    @Override
    public void analyzeCollectorData(Serializable data) throws Exception {
        collection = (HashMap<Integer, Customer>) data;
        if (collection != null) {
            System.out.println("PartitionAnalyzer.analyzeCollectorData()");
            System.out.println(collection.size() + " Customers live in München.");
        }
        stepContext.setPersistentUserData(collection);
    }

    @Override
    public void analyzeStatus(BatchStatus batchStatus, String exitStatus) throws Exception {
        System.out.println(
                "PartitionAnalyzer.analyzeStatus() - batchStatus: " + batchStatus
                        + ", exitStatus: " + exitStatus);
    }
}
