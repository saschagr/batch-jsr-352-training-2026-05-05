package de.mathema.batch.job.myChunkStep.partition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.mathema.batch.job.Customer;
import de.mathema.batch.util.Constants;
import de.mathema.batch.util.DatabaseConfig;
import de.mathema.batch.util.DatabaseConnectionService;
import jakarta.batch.api.partition.PartitionReducer;
import jakarta.batch.runtime.context.JobContext;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;


public class MyPartitionReducer implements PartitionReducer {

    @Inject
    JobContext jobContext;
    HashMap<String, Customer> analyzedData = new HashMap<>();

    @Override
    public void beginPartitionedStep() throws Exception {
        System.out.println("PartitionReducer.beginPartitionedStep()");

          DatabaseConfig config = DatabaseConnectionService.getConnectionConfig();
        System.out.println("Setting up DB...");
        try (Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword())) {
            try (Statement statement = conn.createStatement()) {
                statement.execute("create table IF NOT EXISTS customers (" +
                        "customerId varchar(255) PRIMARY KEY," +
                        "firstName varchar(255), " +
                        "lastName varchar(255), " +
                        "city varchar(255) " +
                        ")");
                statement.execute("delete from customers");
            }
        }
    }

    @Override
    public void beforePartitionedStepCompletion() {
        System.out.println("PartitionReducer.beforePartitionedStepCompletion()");
        analyzedData = (HashMap<String, Customer>) jobContext.getTransientUserData();
        if (analyzedData.size() != 0) {
            System.out.println("PartitionReducer.beforePartitionedStepCompletion(), Anzahl: " + analyzedData.size());
            for(Map.Entry<String, Customer> entry : analyzedData.entrySet())   {
                System.out.println("Key: " + entry.getKey() + ", Customer: " + entry.getValue().getLastName() + ", " + entry.getValue().getFirstName() + ", Wohnort: " + entry.getValue().getCity() );
            };
        }
    }

    @Override
    public void rollbackPartitionedStep() {
        System.out.println("PartitionReducer.rollbackPartitionedStep()");
    }

    @Override
    public void afterPartitionedStepCompletion(PartitionStatus status) {
        analyzedData = (HashMap<String, Customer>) jobContext.getTransientUserData();
        if (analyzedData != null) {
            List<Customer> filteredData = analyzedData.entrySet().stream()
                    .filter(entry -> (entry.getValue().getLastName().equals(Constants.SCHWARZ)))
                    .map(entry -> entry.getValue())
                    .collect(Collectors.toList());

            System.out.println("------------------------------------------------------------------------");
            System.out.println("Filtered data");
            System.out.println("------------------------------------------------------------------------");
            filteredData.forEach(customer -> {
                if (customer != null) {
                    System.out.println(customer.getLastName() + ", " + customer.getFirstName() + ", Wohnsitz: " + customer.getCity());
                }
            });
        }
    }
}

