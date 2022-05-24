package org.raf3k.shared.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("duration")
    public String durationsFormatted() {
        String formattedDuration = String.format("%02d:%02d:%02d.%7d",
                durations().toHours(),
                durations().toMinutesPart(),
                durations().toSecondsPart(),
                durations().toNanos());

        return formattedDuration.substring(0, formattedDuration.length() -3);
    }

    @JsonProperty("bSuccess")
    public Boolean bSuccess() {
        return substeps.stream().allMatch(m -> m.passed);
    }
}
