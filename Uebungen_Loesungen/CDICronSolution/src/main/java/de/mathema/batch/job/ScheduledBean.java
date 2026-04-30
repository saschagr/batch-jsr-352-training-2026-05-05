package de.mathema.batch.job;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped()
@Singleton
public class ScheduledBean {

  @Schedule(second="0",minute="0/3",hour="8-17",dayOfWeek="Mon-Fri")
  private void scheduledMethod(){
    System.out.println("Bean was scheduled!");
  }
}
