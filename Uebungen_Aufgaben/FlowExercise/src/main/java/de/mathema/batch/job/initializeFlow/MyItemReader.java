package de.mathema.batch.job.initializeFlow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import de.mathema.batch.job.Customer;
import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.inject.Named;

@Named
public class MyItemReader extends AbstractItemReader {
  private BufferedReader reader;

  @Override
  public void open(Serializable checkpoint) throws Exception {
    DatabaseConfig dbConfig = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getUsername(), dbConfig.getPassword())) {
      try (Statement statement = conn.createStatement()) {
        statement.execute("CREATE TABLE IF NOT EXISTS customers (" +
          "customerId varchar(255)," +
          "firstName varchar(255), " +
          "lastName varchar(255), " +
          "city varchar(255), " +
          "entryDate varchar(255)" +
          ")");
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
      if (data.length != 5) {
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