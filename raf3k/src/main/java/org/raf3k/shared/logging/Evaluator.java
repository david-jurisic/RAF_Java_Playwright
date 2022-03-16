package org.raf3k.shared.logging;

import org.raf3k.shared.DebugLog;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Evaluator {
    public WebDriver driver;

    public Evaluator() {
    }

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
            Field callersPath = null;
            Field callersAlias = null;

            var callerFields = getAllFields(new LinkedList<Field>(), caller.getClass());

            callersPath = callerFields.stream().filter(f -> f.getName().equalsIgnoreCase("sPath")).findFirst().orElse(null);
            callersAlias = callerFields.stream().filter(f -> f.getName().equalsIgnoreCase("sAlias")).findFirst().orElse(null);

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
            suc.sMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            suc.sMessageAddon = messageAddon;

            List<Field> fields = getAllFields(new ArrayList<Field>(), action.getClass());

            ArrayList<String> lsMethodArguments = new ArrayList<>();
            for (var fieldInfo : fields) {
                if (fieldInfo == fields.stream().findFirst().orElse(null)) continue;
                fieldInfo.setAccessible(true);
                String sValue = fieldInfo.get(action).toString();

                lsMethodArguments.add(sValue);
            }

            suc.sMethodArguments = String.join(", ", lsMethodArguments);

            suc.stepStart = ZonedDateTime.now();
            action.run();
            suc.stepFinish = ZonedDateTime.now();
            suc.bPassed = true;

            return suc;

        } catch (Throwable ex) {
            suc.stepFinish = ZonedDateTime.now();
            suc.Ex = new Exception(ex);
            suc.sMessageAddon = "<br> <b> Error stacktrace: </b></br>" + ex.getStackTrace()[0].toString();
            suc.bPassed = false;
            suc.sScreenshot = getScreenshot();

            return suc;
        }
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> object) {
        fields.addAll(Arrays.asList(object.getDeclaredFields()));

        if (object.getSuperclass() != null)
            getAllFields(fields, object.getSuperclass());

        return fields;
    }
}
