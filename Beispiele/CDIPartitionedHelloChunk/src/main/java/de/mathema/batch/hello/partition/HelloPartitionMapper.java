package de.mathema.batch.hello.partition;

import java.util.Properties;

import de.mathema.batch.hello.HelloData;
import jakarta.batch.api.partition.PartitionMapper;
import jakarta.batch.api.partition.PartitionPlan;
import jakarta.batch.api.partition.PartitionPlanImpl;
import jakarta.inject.Named;

@Named
public class HelloPartitionMapper implements PartitionMapper {

    @Override
    public PartitionPlan mapPartitions() {
        int numOfItems = HelloData.NAMES.size();
        int numOfPartitions = 1;
        int numOfItemPerPartition = numOfItems;


        if(numOfItems%2==0){
            numOfPartitions = 2;
            numOfItemPerPartition = numOfItems/2;
        }

        if(numOfItems%3==0){
            numOfPartitions = 3;
            numOfItemPerPartition = numOfItems/3;
        }

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
