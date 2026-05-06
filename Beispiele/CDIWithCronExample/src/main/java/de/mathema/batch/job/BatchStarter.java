package de.mathema.batch.job;

import java.time.ZoneId;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.batch.operations.JobOperator;
import jakarta.enterprise.concurrent.CronTrigger;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.concurrent.Trigger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped()
public class BatchStarter {

  @Inject
  private JobOperator jobOperator;

  @Resource
  private ManagedScheduledExecutorService scheduler;

  //@PostConstruct
  public void initScheduler() {
    System.out.println("initScheduler");

    Trigger trigger = new CronTrigger("0/5 * * * * *", ZoneId.systemDefault());

    scheduler.schedule(this::startBatch, trigger);
  }

  public void initApplication(
    @Observes @Initialized(ApplicationScoped.class) Object init) {
    System.out.println("initApplication");

    startBatch();
  }

  private void startBatch() {
    System.out.println("startBatch heroBatchJob");
    jobOperator.start("heroBatchJob", null);
  }
}
