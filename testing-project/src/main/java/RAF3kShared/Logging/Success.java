package RAF3kShared.Logging;

import RAF3kShared.ControlObject;

import java.time.LocalDateTime;

public class Success {
    public String sAlias;
    public String sPath;
    public String sMessageAddon;
    public String sMethodName;
    public String sMethodArguments;
    public String sScreenshot;
    public Boolean bPassed;
    public Exception Ex;
    public LocalDateTime StepStart;
    public LocalDateTime StepFinished;

    public Success(ControlObject Caller) {
        StepStart = LocalDateTime.now();
        this.bPassed = true;

        if (Caller != null) {
            //StackTrace stackTrace = new StackTrace();
            this.sPath = Caller.getsPath();
            this.sAlias = Caller.getsAlias();
            //this.sMethodName = stackTrace.GetFrame(1).GetMethod().Name;
        }
    }

    public Success Finish(Exception ex) {
        this.StepFinished = LocalDateTime.now();
        if (ex != null) {
            this.Ex = ex;
            this.bPassed = false;
        }

        return this;
    }

    public void AddMethodDataToLog(ControlObject Caller, String sMethodName) {
        this.sPath = Caller.getsPath();
        this.sAlias = Caller.getsAlias();
        this.sMethodName = sMethodName;

    }

    public void AddArgumentsOfMethodForLog(String sArgumentName, Object oArgumentValue, Boolean bIsOutParameter) {
        String sToAdd = "";
        if (oArgumentValue == null) {
            sMethodArguments = sArgumentName + ": NULL";
            return;
        }

        //sToAdd = $"{(bIsOutParameter?"<i>out</i>":"")}{(oArgumentValue != null?oArgumentValue.ToString() : "NULL")}";
        if (sMethodArguments == null)
            sMethodArguments = sToAdd;
        else
            sMethodArguments += ", " + sToAdd;
    }
}
