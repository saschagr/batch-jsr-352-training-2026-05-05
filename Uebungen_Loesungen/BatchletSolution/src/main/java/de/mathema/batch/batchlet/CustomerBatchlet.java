package de.mathema.batch.batchlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.BatchStatus;

public class CustomerBatchlet extends AbstractBatchlet {
  public final static int CUSTOMER_DATABASE_COLUMN_COUNT = 4;

  @Override
  public String process() {
    System.out.println("Reading input file...");
    DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword()); 
         BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/customer.csv")))) {

      try (Statement statement = conn.createStatement()) {
        statement.execute("create table if not exists customers (" +
            "customerId varchar(255) PRIMARY KEY," +
            "firstName varchar(255), " +
            "lastName varchar(255), " +
            "city varchar(255)) " 
        );
        statement.execute("delete from customers");
      }
      String insertQuery = "INSERT INTO customers (" +
          "customerId, " +
          "firstName, " +
          "lastName, " +
          "city " +
          ") VALUES (?,?,?,?)";

      int columnCount = getColumnCountOfQuery(insertQuery);

      PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

      conn.setAutoCommit(false);

      String line;
      while ( (line = reader.readLine()) != null ) {
        String[] data = line.split(",");
        if (data.length != CUSTOMER_DATABASE_COLUMN_COUNT) {
          continue;
        }

        if (columnCount != 0) {
          for (int i = 1, j = 0; i <= columnCount && j <= columnCount - 1; i++, j++) {
            preparedStatement.setString(i, data[j].trim());
          }
        }
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      conn.commit();

      return BatchStatus.COMPLETED.name();
    } catch (Exception e) {
      System.err.println("Error occurred during batch processing: " + e.getMessage());
      return BatchStatus.FAILED.name();
    }
  }

  private int getColumnCountOfQuery(String insertQuery) {
    int startIndex = insertQuery.indexOf("(");
    int endIndex = insertQuery.indexOf(")");
    if (startIndex != -1 && endIndex != -1) {
      String valuesText = insertQuery.substring(startIndex + 1, endIndex);
      String[] words = valuesText.split(",");
      return words.length;
    } else {
      return 0;
    }
  }
}
