package org.raf3k.shared.logging;

import org.raf3k.shared.ControlObject;

import java.time.ZonedDateTime;

public class Success {
    public String sAlias;
    public String sPath;
    public String sMessageAddon;
    public String sMethodName;
    public String sMethodArguments;
    public String sScreenshot;
    public Boolean bPassed;
    public Exception Ex;
    public ZonedDateTime stepStart;
    public ZonedDateTime stepFinish;

    public Success(ControlObject caller) {
        stepStart = ZonedDateTime.now();
        this.bPassed = true;

        if (caller != null) {
            this.sPath = caller.sPath;
            this.sAlias = caller.sAlias;
            this.sMethodName = Thread.currentThread().getStackTrace()[2].getMethodName().toUpperCase();
        }
    }

    public Success finish(Exception ex) {
        this.stepFinish = ZonedDateTime.now();
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
        String sToAdd;
        if (oArgumentValue == null) {
            sMethodArguments = sArgumentName + ": NULL";
            return;
        }

        sToAdd = bIsOutParameter ? "<i>out</i> " : "";
        sToAdd += oArgumentValue != null ? oArgumentValue.toString() : "NULL";

        if (sMethodArguments == null)
            sMethodArguments = sToAdd;
        else
            sMethodArguments += ", " + sToAdd;
    }
}
