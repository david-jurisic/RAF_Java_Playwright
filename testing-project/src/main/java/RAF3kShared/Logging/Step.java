package RAF3kShared.Logging;

import java.time.Duration;
import java.util.ArrayList;

public class Step {
    public int StepNumber;
    public String StepName;
    public ArrayList<Substep> Substeps = new ArrayList<Substep>();

    public Duration Durations() {
        if(Substeps.size()>0)
            return Duration.between(Substeps.get(0).Start, Substeps.get(Substeps.size() - 1).Finish);

        return Duration.ofSeconds(0);
    }

    public Boolean bSuccess() {
        //return Substeps.Where(s => !s.Passed).ToList().Count == 0;
        return true;
    }
}
