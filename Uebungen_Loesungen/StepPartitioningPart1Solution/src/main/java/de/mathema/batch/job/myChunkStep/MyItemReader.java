package de.mathema.batch.job.myChunkStep;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.BatchProperty;
import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.inject.Inject;

public class MyItemReader extends AbstractItemReader {
    @Inject
    @BatchProperty
    private int firstItem;
    
	@Inject
    @BatchProperty
    private int lastItem;

    private BufferedReader reader;
    private int itemsRead = 0;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        log("Open...");
        InputStream inputStream = this.getClass().getResourceAsStream("/customer.csv");
        reader = new BufferedReader(new InputStreamReader(inputStream));
        if(firstItem > 0){
            int i=0;
            for (; i<firstItem;i++){
                reader.readLine(); // Skip line
            }
            log("Skipped: " + i);
        }

        DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();

        try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword())) {
    
            try (Statement statement = conn.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS customers (" +
                    "customerId varchar(255)," +
                    "firstName varchar(255), " +
                    "lastName varchar(255), " +
                    "city varchar(255) " +
                ")");
            }
        }
    }

    @Override
    public Object readItem() throws Exception {
        String line = reader.readLine();
        if (line != null && itemsRead < (lastItem - firstItem)+1) {
            String[] data = line.split(",");
            itemsRead++;
            return data;
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            log("Lines read: " + itemsRead);
            reader.close();
        }
    }

    private void log(String text) {
        System.out.println(getClass().getSimpleName() + " (" + firstItem + " - " + lastItem + "): "+ text);
    }
}
