package de.mathema.batch.job.decision;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.Decider;
import jakarta.batch.runtime.StepExecution;

public class DiscountDecider implements Decider {
  private ResultSet resultSet;

  @Override
  public String decide(StepExecution[] executions) throws Exception {
    DatabaseConfig dbConfig = DatabaseConnectionService.getConnectionConfig();

    Connection connection = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getUsername(), dbConfig.getPassword());
    Statement statement = connection.createStatement();

    boolean hasCity = false;
    try {
      resultSet = statement.executeQuery("SELECT city FROM customers");

      while (resultSet.next()) {
        String searchedCity = resultSet.getString("city");
        if ("München".equalsIgnoreCase(searchedCity)) {
          hasCity = true;
        }
      }
      return hasCity ? "CALCULATE_EXTRA_DISCOUNT" : "NO_EXTRA_DISCOUNT";

    } finally {
      resultSet.close();
      statement.close();
      connection.close();
    }
  }
}












