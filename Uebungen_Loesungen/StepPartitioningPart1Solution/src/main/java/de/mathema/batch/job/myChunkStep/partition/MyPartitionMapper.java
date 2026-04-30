package de.mathema.batch.job.myChunkStep.partition;

import java.util.Properties;

import jakarta.batch.api.partition.PartitionMapper;
import jakarta.batch.api.partition.PartitionPlan;
import jakarta.batch.api.partition.PartitionPlanImpl;

public class MyPartitionMapper implements PartitionMapper {
 @Override
    public PartitionPlan mapPartitions() {
        int numOfItems = 250;
        int numOfPartitions = 5;
        int numOfItemPerPartition = numOfItems / 5;

        PartitionPlan plan = new PartitionPlanImpl();
        plan.setPartitions(numOfPartitions);

        Properties[] partProps = new Properties[numOfPartitions];

        for(int i=0;i<numOfPartitions;i++){
            Properties props = new Properties();
            props.put("partitionNumber", i);
            props.put("firstItem", String.valueOf(0+i*numOfItemPerPartition));
            props.put("lastItem",String.valueOf((i+1)*numOfItemPerPartition-1));
            partProps[i] = props;
        }
    
        plan.setPartitionProperties(partProps);
    
        return plan;
    }
    
}
