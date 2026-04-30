package de.mathema.batch.job;

import org.apache.batchee.util.Batches;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.operations.JobStartException;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.batch.runtime.BatchStatus;
import jakarta.batch.runtime.JobExecution;

public class MainApp {
  public static void main(String[] args) throws InterruptedException {
    try {
      long startTime = System.currentTimeMillis();

      JobOperator jobOperator = BatchRuntime.getJobOperator();
      long executionId = jobOperator.start("batchProcessingJob", null);

      JobExecution jobExecution = jobOperator.getJobExecution(executionId);
      System.out.println("Job Status: " + jobExecution.getBatchStatus());
      Batches.waitFor(executionId);
      System.out.println("Exit Status: " + jobExecution.getExitStatus());
      if (jobExecution.getBatchStatus().equals(BatchStatus.COMPLETED)) {
        System.out.println("Batch job completed successfully.");
        printProcessedTime(startTime);
      } else {
        System.out.println("Batch job failed with exit status: " + jobExecution.getExitStatus());
      }
    } catch (JobStartException e) {
      e.printStackTrace();
    }

  }
    private static void printProcessedTime(long startTime) {
      long endTime = System.currentTimeMillis();
      long elapsedTime = endTime - startTime;
      System.out.println("Elapsed time: " + elapsedTime + " milliseconds");
    }
}
