package de.mathema.batch.decision;

import jakarta.batch.api.Decider;
import jakarta.batch.runtime.StepExecution;

public class RabattDecider implements Decider {

    @Override
	public String decide(StepExecution[] executions) {
        return switch (executions[0].getExitStatus()) {
            case "ACTIVE" -> "MEMBER";
            case "CANCELED" -> "STANDARD";
            case "REQUESTED" -> "MEMBER";
            case "NONE" -> "STANDARD";
            default -> "STANDARD";
        };
	}	
}