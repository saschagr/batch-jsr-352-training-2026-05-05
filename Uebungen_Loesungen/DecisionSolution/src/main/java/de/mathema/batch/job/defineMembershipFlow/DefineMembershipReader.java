package de.mathema.batch.job.defineMembershipFlow;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import de.mathema.batch.job.Customer;
import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.chunk.AbstractItemReader;

public class DefineMembershipReader extends AbstractItemReader {
  private ResultSet resultSet;
  private Connection connection;
  private Statement statement;

  @Override
  public void open(Serializable checkpoint) throws Exception {
    DatabaseConfig dbConfig = DatabaseConnectionService.getConnectionConfig();
    connection = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getUsername(), dbConfig.getPassword());

    statement = connection.createStatement();
    resultSet = statement.executeQuery("SELECT * FROM customers");
  }

  @Override
  public Object readItem() throws Exception {
    if (resultSet.next()) {
      return new Customer(
        resultSet.getString("customerId"),
        resultSet.getString("firstName"),
        resultSet.getString("lastName"),
        resultSet.getString("city"),
        resultSet.getString("entryDate")
      );
    }
    return null;
  }

  @Override
  public void close() throws Exception {
    resultSet.close();
    statement.close();
    connection.close();
  }
}
