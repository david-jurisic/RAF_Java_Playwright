package RAF3kShared.Logging;

import RAF3kShared.DebugLog;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evaluator {
    public WebDriver driver;

    public Evaluator(WebDriver _driver) {
        driver = _driver;
    }

    private String GetScreenshot() {
        try {
            if (driver == null)
                return "";
            TakesScreenshot ss = ((TakesScreenshot) driver);
            return ss.getScreenshotAs(OutputType.BASE64);
        } catch (Exception ex) {
            DebugLog.Add(ex);
            return "";
        }
    }

    public String GenerateCucumberErrorReport(Step CurrentStep) {
        try {
            String sError = "Error running step*****" + CurrentStep.StepName;
            for (Substep step : CurrentStep.Substeps) {
                sError += System.lineSeparator();
                sError += step.Name;
                if (!step.Passed) {
                    sError += System.lineSeparator();
                    sError += step.Ex.getMessage();
                    sError += System.lineSeparator();

                }
            }
            return sError;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public Success Evaluate(Runnable action, Object caller, String MessageAddon) {
        Success suc = new Success(null);

        try {
            String CallersPathvalue = "";
            String CallersAliasValue = "";

            Field CallersPath = caller.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("sPath");
            Field CallersAlias = caller.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("sAlias");

            if (CallersPath != null) {
                CallersPath.setAccessible(true);
                CallersPathvalue = (String) CallersPath.get(caller);
            }

            if (CallersAlias != null) {
                CallersAlias.setAccessible(true);
                String oCaller = (String) CallersAlias.get(caller);
                CallersAliasValue = oCaller == null ? "NULL" : oCaller;
            }

            suc.sPath = CallersPathvalue;
            suc.sAlias = CallersAliasValue;
            //if (action.toString().contains("<") && action.toString().contains(">"))
            //suc.sMethodName = action.Method.Name.Substring(action.Method.Name.IndexOf("<")+1, action.Method.Name.IndexOf(">") - action.Method.Name.IndexOf("<")-1);
            //suc.sMethodName = action.toString();
            //else
            suc.sMethodName = action.toString();
            suc.sMessageAddon = MessageAddon;

            Field args = action.getClass().getDeclaredField("arg$1");
            args.setAccessible(true);
            Field[] childFields = args.get(action).getClass().getSuperclass().getFields();
            ArrayList<String> lsMethodArguments = new ArrayList<>();
            for (var fieldInfo : childFields) {
                fieldInfo.setAccessible(true);
                String sFieldName = fieldInfo.getName();
                if (sFieldName.startsWith("<>"))
                    continue;
                String oVal = fieldInfo.get(caller).toString();
                String sValue = oVal == null ? "NULL" : oVal;

                lsMethodArguments.add(sValue);
            }

            suc.sMethodArguments = String.join(", ", lsMethodArguments);

            suc.StepStart = LocalDateTime.now();
            action.run();
            suc.StepFinish = LocalDateTime.now();
            suc.bPassed = true;
            return suc;

        } catch (Exception ex) {
            suc.StepFinish = LocalDateTime.now();
            suc.Ex = ex;
            suc.sMessageAddon = "<br> <b> Error stacktrace: </b></br>" + ex.getMessage();
            suc.bPassed = false;
            suc.sScreenshot = GetScreenshot();
            return suc;
        }
    }
}
