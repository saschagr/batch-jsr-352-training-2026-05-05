package de.mathema.batch.job;

import jakarta.batch.operations.JobOperator;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped()
@Singleton
public class BatchStarter {

  @Inject
  private JobOperator jobOperator;
/* 
  @Resource
  private ManagedScheduledExecutorService scheduler;

  public void initApplication(
    @Observes @Initialized(ApplicationScoped.class) Object init) {
    System.out.println("initApplication");
    startBatch();
  }
*/

  @Schedule(second="*/30", minute="*", hour="*")
  private void startBatch() {
    System.out.println("startBatch customerBatchJob");
    jobOperator.start("customerBatchJob", null);
  }
}
