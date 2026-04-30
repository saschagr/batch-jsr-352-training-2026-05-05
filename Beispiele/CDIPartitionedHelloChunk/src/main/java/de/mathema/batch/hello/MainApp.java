package de.mathema.batch.hello;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.JobExecution;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Singleton
public class MainApp {

  @Inject
  private JobOperator jobOperator;

  // Workaround if EJB is not anoption
  // public void initApplication(
  //   @Observes @Initialized(ApplicationScoped.class) Object init) {
  //   System.out.println("initApplication");

  //   scheduleBatchJob();
  // }

  @Schedule(second="0/10",minute="*",hour="*")
  public void scheduleBatchJob() {
    long executionId = jobOperator.start("helloJob", null);

    JobExecution jobExecution = jobOperator.getJobExecution(executionId);
    System.out.println("Job Status: " + jobExecution.getBatchStatus());
  }
}
