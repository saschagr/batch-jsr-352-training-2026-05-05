package de.mathema.batch.util;

import java.util.ResourceBundle;

public class DatabaseConnectionService {

  private static final ResourceBundle properties = ResourceBundle.getBundle("application");

  public static DatabaseConfig getConnectionConfig() {
    String jdbcUrl = properties.getString("jdbc.url");
    String username = properties.getString("jdbc.username");
    String password = properties.getString("jdbc.password");
    return new DatabaseConfig(jdbcUrl, username, password);
  }
}