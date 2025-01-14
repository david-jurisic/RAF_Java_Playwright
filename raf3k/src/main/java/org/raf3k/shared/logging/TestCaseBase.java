package org.raf3k.shared.logging;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.shared.ConfigurationHelper;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.testdata.TestDataHelper;

import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCaseBase {
    public ArrayList<Step> steps = new ArrayList<Step>();
    public String sTestCaseName;
    public String sTestCaseCode;
    public String sTestCaseAuthor;
    public Boolean bPass = true;
    private Step currentStep;
    private final Evaluator eval = new Evaluator(null);
    private int bDDStepNumber = 1;

    public TestCaseBase() {
        sTestCaseName = "Not Defined";
        sTestCaseCode = "Not Defined";
        sTestCaseAuthor = "Not Defined";
        DebugLog.add("Warning *** No RAFtestCase attribute found, log data might be inconclusive", 0);

        SharedVariables.configuration = new ConfigurationHelper();
        SharedVariables.testData = new TestDataHelper();
        DebugLog.add("Running test: " + this.toString(), 2);
    }

    public TestCaseBase(String testCaseName, String testCaseCode, String testCaseAuthor) {
        sTestCaseName = testCaseName;
        sTestCaseCode = testCaseCode;
        sTestCaseAuthor = testCaseAuthor;

        SharedVariables.configuration = new ConfigurationHelper();
        SharedVariables.testData = new TestDataHelper();
    }

    public void newStep(int iNumber, String sName) {
        if (currentStep != null)
            steps.add(currentStep);

        currentStep = new Step();
        currentStep.stepNumber = iNumber;
        currentStep.stepName = sName;
        currentStep.substeps = new ArrayList<>();
        DebugLog.add("Added new step: " + iNumber + " : " + sName, 2);
    }

    public void addBDDStep(String sName) {
        currentStep = new Step();
        currentStep.stepNumber = bDDStepNumber++;
        currentStep.stepName = sName;
        if (currentStep != null)
            steps.add(currentStep);

    }

    public void finishBDDStep() {
        if (!currentStep.bSuccess() && SharedVariables.configuration.getProperty("stopOnError") == "true") {
                throw new RuntimeException(eval.generateCucumberErrorReport(currentStep));
        }
    }

    public void finishBDDScenario() {
        LogConstructor.generateLog(this);

        for (Step step : steps) {
            if (!step.bSuccess()) {
                throw new RuntimeException(eval.generateCucumberErrorReport(currentStep));
            }
        }
    }

    public Success success(Success newSuccess) {

        Substep Step = new Substep();
        Step.start = newSuccess.stepStart;
        Step.finish = newSuccess.stepFinish;
        Step.ex = newSuccess.Ex;
        Step.passed = newSuccess.bPassed;
        Step.screenshot = newSuccess.sScreenshot;
        Step.messageAddon = newSuccess.sMessageAddon;

        if (this.getClass() == APITestCase.class)
            Step.name = newSuccess.sPath + newSuccess.sAlias + "." + newSuccess.sMethodName +
                    "(" + (newSuccess.sMethodArguments != null ? newSuccess.sMethodArguments : "") + ")";
        else
            Step.name = newSuccess.sPath + "." + newSuccess.sAlias + "." + newSuccess.sMethodName + "(" + newSuccess.sMethodArguments + ")";

        currentStep.substeps.add(Step);

        if (!Step.passed) {
            this.bPass = false;

            String sStopOnError = SharedVariables.configuration.getProperty("stopOnError");
            if (!sStopOnError.equalsIgnoreCase("false")) {
                DebugLog.add("Step failed. Finishing test case...", 1);
                Assertions.fail(Step.ex.getMessage());
            }
        }

        return newSuccess;
    }

    @AfterAll
    public void tearDown() {
        if (currentStep != null)
            steps.add(currentStep);
        LogConstructor.generateLog(this);
    }
}

