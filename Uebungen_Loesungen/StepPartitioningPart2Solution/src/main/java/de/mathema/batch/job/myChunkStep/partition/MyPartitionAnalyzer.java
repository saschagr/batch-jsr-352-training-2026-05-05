package de.mathema.batch.job.myChunkStep.partition;

import java.io.Serializable;
import java.util.HashMap;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.partition.PartitionAnalyzer;
import jakarta.batch.runtime.BatchStatus;
import jakarta.batch.runtime.context.JobContext;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class MyPartitionAnalyzer implements PartitionAnalyzer {
    HashMap<String, Customer> collection = new HashMap<>();

    @Inject
    JobContext jobContext;

    @Override
    public void analyzeCollectorData(Serializable data) throws Exception {
        HashMap<String, Customer> collectorData = (HashMap<String, Customer>) data;
        if (collection != null) {
            System.out.println("PartitionAnalyzer.analyzeCollectorData()");
            System.out.println(collection.size() + " Customers live in München.");
        }
        if (collectorData != null) {
        	collection.putAll(collectorData);
        }
        
        jobContext.setTransientUserData(collection);
    }

    @Override
    public void analyzeStatus(BatchStatus batchStatus, String exitStatus) throws Exception {
        System.out.println(
                "PartitionAnalyzer.analyzeStatus() - batchStatus: " + batchStatus
                        + ", exitStatus: " + exitStatus);
    }
}
