package de.mathema.batch.job.defineMembershipFlow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import de.mathema.batch.job.Customer;
import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.chunk.AbstractItemWriter;

public class DefineMembershipWriter extends AbstractItemWriter {

  @Override
  public void writeItems(List<Object> items) throws Exception {

    DatabaseConfig dbConfig = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getUsername(), dbConfig.getPassword())) {
      String updateQuery = 
        "UPDATE customers SET " +
        "membership = ? " +
        "WHERE customerId = ?";
      PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

      for (Object item : items) {
        Customer data = (Customer) item;
        preparedStatement.setString(1, data.getMembership());
        preparedStatement.setString(2, data.getCustomerId());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      conn.commit();
    }
  }

}