package org.raf3k.shared.logging;

import org.raf3k.shared.DebugLog;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evaluator {
    public WebDriver driver;

    public Evaluator(WebDriver _driver) {
        driver = _driver;
    }

    private String getScreenshot() {
        try {
            if (driver == null)
                return "";
            TakesScreenshot ss = ((TakesScreenshot) driver);
            return ss.getScreenshotAs(OutputType.BASE64);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return "";
        }
    }

    public String generateCucumberErrorReport(Step currentStep) {
        try {
            String sError = "Error running step*****" + currentStep.stepName;
            for (Substep step : currentStep.substeps) {
                sError += System.lineSeparator();
                sError += step.name;
                if (!step.passed) {
                    sError += System.lineSeparator();
                    sError += step.ex.getMessage();
                    sError += System.lineSeparator();

                }
            }
            return sError;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public Success evaluate(Runnable action, Object caller, String messageAddon) {
        Success suc = new Success(null);

        try {
            String callersPathValue = "";
            String callersAliasValue = "";

            Field callersPath = caller.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("sPath");
            Field callersAlias = caller.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("sAlias");

            if (callersPath != null) {
                callersPath.setAccessible(true);
                callersPathValue = (String) callersPath.get(caller);
            }

            if (callersAlias != null) {
                callersAlias.setAccessible(true);
                String oCaller = (String) callersAlias.get(caller);
                callersAliasValue = oCaller == null ? "NULL" : oCaller;
            }

            suc.sPath = callersPathValue;
            suc.sAlias = callersAliasValue;
            //if (action.toString().contains("<") && action.toString().contains(">"))
            //suc.sMethodName = action.Method.Name.Substring(action.Method.Name.IndexOf("<")+1, action.Method.Name.IndexOf(">") - action.Method.Name.IndexOf("<")-1);
            //suc.sMethodName = action.toString();
            //else
            suc.sMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            suc.sMessageAddon = messageAddon;

            Field args = action.getClass().getDeclaredField("arg$1");
            args.setAccessible(true);
            Field[] childFields = args.get(action).getClass().getSuperclass().getFields();
            ArrayList<String> lsMethodArguments = new ArrayList<>();
            for (var fieldInfo : childFields) {
                fieldInfo.setAccessible(true);
                String sFieldName = fieldInfo.getName();
                if (sFieldName.startsWith("<>"))
                    continue;
                var oVal = fieldInfo.get(caller);
                String sValue = oVal == null ? "NULL" : oVal.toString();

                lsMethodArguments.add(sValue);
            }

            suc.sMethodArguments = String.join(", ", lsMethodArguments);

            suc.stepStart = LocalDateTime.now();
            action.run();
            suc.stepFinish = LocalDateTime.now();
            suc.bPassed = true;

            return suc;

        } catch (Exception ex) {
            suc.stepFinish = LocalDateTime.now();
            suc.Ex = ex;
            suc.sMessageAddon = "<br> <b> Error stacktrace: </b></br>" + ex.getMessage();
            suc.bPassed = false;
            suc.sScreenshot = getScreenshot();

            return suc;
        }
    }
}
