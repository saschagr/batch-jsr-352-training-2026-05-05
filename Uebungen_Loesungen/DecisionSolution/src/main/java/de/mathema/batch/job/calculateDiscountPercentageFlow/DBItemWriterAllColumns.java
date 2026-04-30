package de.mathema.batch.job.calculateDiscountPercentageFlow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import de.mathema.batch.job.Customer;
import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.chunk.AbstractItemWriter;

public class DBItemWriterAllColumns extends AbstractItemWriter {

  @Override
  public void writeItems(List<Object> items) throws Exception {
    DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword())) {
      String updateQuery = "UPDATE customers SET " +
        "customerId = ?, " +
        "firstName = ?, " +
        "lastName = ?, " +
        "city = ?, " +
        "entryDate = ?, " +
        "membership = ?, " +
        "discountpercentage = ? " +
        "WHERE customerId = ?";
      PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

      for (Object item : items) {
        Customer data = (Customer) item;
        preparedStatement.setString(1, data.getCustomerId());
        preparedStatement.setString(2, data.getFirstName());
        preparedStatement.setString(3, data.getLastName());
        preparedStatement.setString(4, data.getCity());
        preparedStatement.setString(5, data.getEntryDate());
        preparedStatement.setString(6, data.getMembership());
        preparedStatement.setString(7, data.getDiscountPercentage());
        preparedStatement.setString(8, data.getCustomerId());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      conn.commit();
    }
  }

}
