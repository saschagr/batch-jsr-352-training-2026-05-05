package de.mathema.batch.split.batchlet;

/* 
 * For example purpose only! 
 * This is NOT a valid pattern to be used in production code!
 **/
@SuppressWarnings("unused")
public class WorkingData {

    public static WorkingData INSTANCE = new WorkingData();

    String fastResult;
    String mediumResult;
    String slowResult;

    public static void reset(){
        INSTANCE = new WorkingData();
    }
}
