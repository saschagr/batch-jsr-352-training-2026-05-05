package de.mathema.batch.split.batchlet;

public class MediumWorkingBatchlet extends AbstractWorkBatchlet {

  public MediumWorkingBatchlet() {
    super(10,500);
  }

  @Override
  public String process() throws Exception{
    String exitStatus = super.process();
    WorkingData.INSTANCE.mediumResult="Right in the middle ;)";
    return exitStatus;
  }
}
