package de.mathema.batch.job;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.inject.Named;

@Named
public class CustomerItemReader extends AbstractItemReader {
  private BufferedReader reader;
  public final static int CUSTOMER_DATABASE_COLUMN_COUNT = 4;

  @Override
  public void open(Serializable checkpoint) throws Exception {

    DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword())) {
      try (Statement statement = conn.createStatement()) {
        statement.execute("CREATE TABLE IF NOT EXISTS customers (" +
            "customerId varchar(255)," +
            "firstName varchar(255), " +
            "lastName varchar(255), " +
            "city varchar(255))" +
          "");
        statement.execute("DELETE FROM customers");
      }

      InputStream inputStream = this.getClass().getResourceAsStream("/customer.csv");
      reader = new BufferedReader(new InputStreamReader(inputStream));
    }
  }

  @Override
  public Object readItem() throws Exception {
    String line = reader.readLine();
    while (line != null) {
      String[] data = line.split(",");
      if (data.length != CUSTOMER_DATABASE_COLUMN_COUNT) {
        continue;
      }
      return data;
    }
    return null;
  }

  @Override
  public void close() throws Exception {
    if (reader != null) {
      reader.close();
    }
  }
}
