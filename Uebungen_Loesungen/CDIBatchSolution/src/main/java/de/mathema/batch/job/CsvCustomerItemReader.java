package de.mathema.batch.job;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.inject.Named;

//@RequestScoped
@Named
public class CsvCustomerItemReader extends AbstractItemReader {
  private BufferedReader reader;

  @Override
  public void open(Serializable checkpoint) throws Exception {
    reader =
      new BufferedReader(
        new InputStreamReader(
          this.getClass().getResourceAsStream("/customer.csv")));
  }

  @Override
  public Object readItem() throws Exception {
    String line = reader.readLine();
    while (line != null) {
      String[] data = line.split(",");
      if (data.length != 4) {
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
