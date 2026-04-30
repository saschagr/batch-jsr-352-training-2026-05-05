package de.mathema.batch.hello.batchlet;

import jakarta.batch.api.AbstractBatchlet;

public class HelloBatchlet extends AbstractBatchlet {
  
  @Override
  public String process() {
  	System.out.println("Batchlet says Hello!");
    return "OK";
  }
}
