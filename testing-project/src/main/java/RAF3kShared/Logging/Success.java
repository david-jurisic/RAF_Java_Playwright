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
    public LocalDateTime stepStart;
    public LocalDateTime stepFinish;

    public Success(ControlObject caller) {
        stepStart = LocalDateTime.now();
        this.bPassed = true;

        if (caller != null) {
            //StackTrace stackTrace = new StackTrace();
            this.sPath = caller.sPath;
            this.sAlias = caller.sAlias;
            //this.sMethodName = stackTrace.GetFrame(1).GetMethod().Name;
        }
    }

    public Success finish(Exception ex) {
        this.stepFinish = LocalDateTime.now();
        if (ex != null) {
            this.Ex = ex;
            this.bPassed = false;
        }

        return this;
    }

    public void addMethodDataToLog(ControlObject caller, String sMethodName) {
        this.sPath = caller.sPath;
        this.sAlias = caller.sAlias;
        this.sMethodName = sMethodName;

    }

    public void addArgumentsOfMethodForLog(String sArgumentName, Object oArgumentValue, Boolean bIsOutParameter) {
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
