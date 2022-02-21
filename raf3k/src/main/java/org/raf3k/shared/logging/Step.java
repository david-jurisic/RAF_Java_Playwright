package org.raf3k.shared.logging;

import java.time.Duration;
import java.util.ArrayList;

public class Step {
    public int stepNumber;
    public String stepName;
    public ArrayList<Substep> substeps = new ArrayList<Substep>();

    public Duration durations() {
        if(substeps.size()>0)
            return Duration.between(substeps.get(0).start, substeps.get(substeps.size() - 1).finish);

        return Duration.ofSeconds(0);
    }

    public Boolean bSuccess() {
        return substeps.stream().allMatch(m -> m.passed);
    }
}
