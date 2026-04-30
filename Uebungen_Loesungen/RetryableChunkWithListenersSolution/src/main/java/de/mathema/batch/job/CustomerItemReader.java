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

	public static class CustomerCheckpoint implements Serializable{
		private int linesRead = 0;
	}
	
  private CustomerCheckpoint customerCheckpoint;
  private BufferedReader reader;
  public final static int CUSTOMER_DATABASE_COLUMN_COUNT = 4;

  @Override
  public void open(Serializable checkpoint) throws Exception {
	 
    System.out.println("Start reading...");

	  customerCheckpoint = checkpoint!=null?(CustomerCheckpoint)checkpoint:new CustomerCheckpoint(); 

    DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();

    try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword())) {
 
    if(customerCheckpoint.linesRead == 0) {	
      try (Statement statement = conn.createStatement()) {
        statement.execute("CREATE TABLE IF NOT EXISTS customers (" +
            "customerId varchar(255)," +
            "firstName varchar(255), " +
            "lastName varchar(255), " +
            "city varchar(255) " +
          ")");
        statement.execute("DELETE FROM customers");
      }
    }


      InputStream inputStream = this.getClass().getResourceAsStream("/customer.csv");
      reader = new BufferedReader(new InputStreamReader(inputStream));

      if(customerCheckpoint.linesRead>0) {
    	  for (int i=1;i<customerCheckpoint.linesRead+1;i++) {
    		  reader.readLine(); // Skip line
    	  }
      }
      
    }
  }

  @Override
  public Object readItem() throws Exception {
	  
	if(Math.random() < 0.05) {
		System.out.println("BOOM");
		throw new java.lang.IllegalStateException();
	}
	  
    String line = reader.readLine();
    if (line != null) {
      String[] data = line.split(",");
      if (data.length != CUSTOMER_DATABASE_COLUMN_COUNT) {
        return null;
      }

      customerCheckpoint.linesRead++;
      return data;
    }
    return null;
  }

  @Override
  public void close() throws Exception {
    if (reader != null) {
      reader.close();
    }
    System.out.println("Reading completed.");
  }

	@Override
	public Serializable checkpointInfo() {
		return customerCheckpoint;
	}
  
}
