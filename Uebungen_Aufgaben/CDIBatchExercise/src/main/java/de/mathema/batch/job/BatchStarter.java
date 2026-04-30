package de.mathema.batch.job;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;

@ApplicationScoped()
public class BatchStarter {

  @PostConstruct
  public void initScheduler() {
  }

  public void initApplication(@Observes @Initialized(ApplicationScoped.class) Object init) {

  }

  private void startBatch() {

  }
}
