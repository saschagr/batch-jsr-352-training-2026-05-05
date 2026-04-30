package de.mathema.batch.workday.batchlet;

import jakarta.batch.api.AbstractBatchlet;

public abstract class AbstractEchoBatchlet extends AbstractBatchlet {
  
  private final String echo;
  private final String exitStatus;

  protected AbstractEchoBatchlet(String echo) {
    this(echo,"OK");
  }

  protected AbstractEchoBatchlet(String echo, String exitStatus) {
    this.echo = echo;
    this.exitStatus = exitStatus;
  }

  @Override
  public final String process() {
  	System.out.println(echo);
    return exitStatus;
  }
}
