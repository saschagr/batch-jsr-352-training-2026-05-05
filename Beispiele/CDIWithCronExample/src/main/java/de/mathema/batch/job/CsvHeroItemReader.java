package de.mathema.batch.job;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named//(value = "csvHeroItemReader")
@RequestScoped
public class CsvHeroItemReader extends AbstractItemReader {
  private BufferedReader reader;
  
  public final static int CUSTOMER_DATABASE_COLUMN_COUNT = 9;
  
  @Override
  public void open(Serializable checkpoint) throws Exception {
    reader =
      new BufferedReader(
        new InputStreamReader(
          this.getClass().getResourceAsStream("/heroes.csv")));
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
