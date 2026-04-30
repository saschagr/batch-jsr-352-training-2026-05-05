package de.mathema.batch.decision.batchlet;

public class KauferMitgliedschaftPruefenBatchlet extends AbstractEchoBatchlet {
  
  public KauferMitgliedschaftPruefenBatchlet() {
    super("Prüfe Mitgliedschaft... ", new String[]{"NONE","ACTIVE"}[(int)(Math.random() * 2)]);
  }

}
