package de.mathema.batch.split.batchlet;

public class FastWorkingBatchlet extends AbstractWorkBatchlet {

  public FastWorkingBatchlet() {
    super(20,250);
  }

  @Override
  public String process() throws Exception{
    String exitStatus = super.process();
    WorkingData.INSTANCE.fastResult="Oh so fast!";
    return exitStatus;
  }
}
