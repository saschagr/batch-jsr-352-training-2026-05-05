package de.mathema.batch.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.chunk.AbstractItemWriter;

public class CustomerItemWriter extends AbstractItemWriter {

  @Override
  public void writeItems(List<Object> items) throws Exception {
    DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword())) {
      String insertQuery = "INSERT INTO customers (" +
          "customerId, " +
          "firstName, " +
          "lastName, " +
          "city " +
        ") VALUES (?,?,?,?)";
      PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

      for (Object item : items) {
        Customer data = (Customer) item;
        preparedStatement.setString(1, data.getCustomerId());
        preparedStatement.setString(2, data.getFirstName());
        preparedStatement.setString(3, data.getLastName());
        preparedStatement.setString(4, data.getCity());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      conn.commit();
    }
  }
}
