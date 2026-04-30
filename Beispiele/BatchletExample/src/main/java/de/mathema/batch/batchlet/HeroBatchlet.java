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

public class HeroBatchlet extends AbstractBatchlet {
  public final static int CUSTOMER_DATABASE_COLUMN_COUNT = 9;
  @Override
  public String process() {
    System.out.println("Reading input file...");
    DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
      BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/heroes.csv")))) {

      try (Statement statement = conn.createStatement()) {
        statement.execute("CREATE TABLE IF NOT EXISTS people (" +
          "heroName varchar(255)," +
          "realName varchar(255), " +
          "address varchar(255), " +
          "country varchar(255), " +
          "powerLevel varchar(255), " +
          "team varchar(255), " +
          "affiliation varchar(255), " +
          "weapon varchar(255), " +
          "origin varchar(255)) "
        );
        statement.execute("DELETE FROM people");
      }
      String insertQuery = "INSERT INTO people (heroName, realName, address, country, powerlevel, team, affiliation, weapon, origin) VALUES (?,?,?,?,?,?,?,?,?)";
      int columnCount = getColumnCountOfQuery(insertQuery);

      PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

      conn.setAutoCommit(false);

      String line;
      while ((line = reader.readLine()) != null) {
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
