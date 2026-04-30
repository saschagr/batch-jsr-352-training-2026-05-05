package de.mathema.batch.split.batchlet;

public class SlowWorkingBatchlet extends AbstractWorkBatchlet {

  public SlowWorkingBatchlet() {
    super(15,700);
  }

  @Override
  public String process() throws Exception{
    String exitStatus = super.process();
    WorkingData.INSTANCE.slowResult="Slow but correct!";
    return exitStatus;
  }

}
