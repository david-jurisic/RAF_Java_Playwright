package RAF3kShared.Logging;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
            System.out.println(ex);
            return "";
        }
    }

    //public Success Evaluate(Action action, Object caller, string MessageAddon = "") {}

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
}
