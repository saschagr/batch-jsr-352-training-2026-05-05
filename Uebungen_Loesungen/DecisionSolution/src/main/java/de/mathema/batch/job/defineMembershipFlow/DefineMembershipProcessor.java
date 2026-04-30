package de.mathema.batch.job.defineMembershipFlow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.mathema.batch.job.Customer;
import jakarta.batch.api.chunk.ItemProcessor;

public class DefineMembershipProcessor implements ItemProcessor {
  @Override
  public Object processItem(Object item) {
    Customer currentCustomer = (Customer) item;

    String dateString = currentCustomer.getEntryDate();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    try {
      int yearDifference = calculateYearDifference(dateString, dateFormat);

      if(yearDifference >= 8) {
        currentCustomer.setMembership("Premium");
        return currentCustomer;
      } else {
        currentCustomer.setMembership("Basic");
      }
    } catch (ParseException e) {
      System.out.println("Invalid date format: " + e.getMessage());
    }
    return currentCustomer;
  }

  private static int calculateYearDifference(String dateString, SimpleDateFormat dateFormat) throws ParseException {
    Date date = dateFormat.parse(dateString);
    Calendar currentCalendar = Calendar.getInstance();
    Calendar dateCalendar = Calendar.getInstance();
    dateCalendar.setTime(date);
    int yearDifference = currentCalendar.get(Calendar.YEAR) - dateCalendar.get(Calendar.YEAR);
    return yearDifference;
  }
}
