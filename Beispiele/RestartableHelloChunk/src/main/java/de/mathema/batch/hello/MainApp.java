package de.mathema.batch.hello;

import org.apache.batchee.util.Batches;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.operations.JobStartException;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.batch.runtime.BatchStatus;
import jakarta.batch.runtime.JobExecution;

public class MainApp {
  public static void main(String[] args) throws InterruptedException {
    try {
      JobOperator jobOperator = BatchRuntime.getJobOperator();
      long executionId = jobOperator.start("helloJob", null);

      JobExecution jobExecution = jobOperator.getJobExecution(executionId);
      System.out.println("Job Status: " + jobExecution.getBatchStatus());
      
      Batches.waitFor(executionId);
      
      System.out.println("Exit Status: " + jobExecution.getExitStatus());
      
      if(BatchStatus.COMPLETED == jobExecution.getBatchStatus()) {
        System.out.println("Batch job completed successfully.");
      } else {
        System.out.println("Batch job failed with exit status: " + jobExecution.getExitStatus());
      }
    } catch (JobStartException e) {
      e.printStackTrace();
    }
  }
}
