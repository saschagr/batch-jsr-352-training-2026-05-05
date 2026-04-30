package de.mathema.batch.job.myChunkStep;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

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
