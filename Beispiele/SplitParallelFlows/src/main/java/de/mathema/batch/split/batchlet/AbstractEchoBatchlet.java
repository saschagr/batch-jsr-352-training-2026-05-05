package de.mathema.batch.split.batchlet;

import jakarta.batch.api.AbstractBatchlet;

public abstract class AbstractEchoBatchlet extends AbstractBatchlet {
  
  private final String echo;

  protected AbstractEchoBatchlet(String echo) {
    this.echo = echo;
  }

  @Override
  public final String process() {
  	System.out.println(echo);
    return "OK";
  }
}
