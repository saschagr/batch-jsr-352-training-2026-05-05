package de.mathema.batch.job.addColumnsFlow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.BatchStatus;

public class BatchletDiscountPercentageColumn extends AbstractBatchlet {

  @Override
  public String process() {
    DatabaseConfig dbConfig = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getUsername(), dbConfig.getPassword())) {
      String addColumnQuery = "ALTER TABLE customers ADD IF NOT EXISTS discountpercentage varchar(255)";

      PreparedStatement preparedStatement = conn.prepareStatement(addColumnQuery);
      preparedStatement.addBatch();

      preparedStatement.executeBatch();
      conn.commit();
      return BatchStatus.COMPLETED.name();
    } catch (Exception e) {
      System.err.println("Error occurred during batch processing: " + e.getMessage());
      return BatchStatus.FAILED.name();
    }
  }
}
