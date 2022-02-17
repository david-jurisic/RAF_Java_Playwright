package RAF3kShared.Logging;

import RAF3kShared.ConfigurationHelper;
import RAF3kShared.DebugLog;
import RAF3kShared.RAFtestCaseAttribute;
import RAF3kShared.SharedVariables;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCaseBase {
    public ArrayList<Step> Steps = new ArrayList<Step>();
    private Step CurrentStep;
    private Evaluator Eval = new Evaluator(null);
    public String sTestCaseName;
    public String sTestCaseCode;
    public String sTestCaseAuthor;
    private int BDDStepNumber = 1;
    public Boolean bPass = true;

    public TestCaseBase() {

        sTestCaseName = "Not Defined";
        sTestCaseCode = "Not Defined";
        sTestCaseAuthor = "Not Defined";
        DebugLog.Add("Warning *** No RAFtestCase attribute found, log data might be inconclusive", 0);

        SharedVariables.Configuration = new ConfigurationHelper();
        //SharedVariables.TestData = new TestDataHelper();
    }

    public TestCaseBase(String TestCaseName, String TestCaseCode, String TestCaseAuthor) {

        sTestCaseName = TestCaseName;
        sTestCaseCode = TestCaseCode;
        sTestCaseAuthor = TestCaseAuthor;

        //string path = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "Settings", @"Settings.xml");
        SharedVariables.Configuration = new ConfigurationHelper();
        //SharedVariables.TestData = new TestDataHelper();
    }

    public void NewStep(int iNumber, String sName) {
        if (CurrentStep != null)
            Steps.add(CurrentStep);

        CurrentStep = new Step();
        CurrentStep.StepNumber = iNumber;
        CurrentStep.StepName = sName;
        CurrentStep.Substeps = new ArrayList<>();
        DebugLog.Add("Added new step: " + String.valueOf(iNumber) + " : " + sName, 2);
    }

    public void AddBDDStep(String sName) {
        CurrentStep = new Step();
        CurrentStep.StepNumber = BDDStepNumber++;
        CurrentStep.StepName = sName;
        if (CurrentStep != null)
            Steps.add(CurrentStep);

    }

    public void FinishBDDStep() throws Exception {
        if (!CurrentStep.bSuccess()) {
            throw new Exception(Eval.GenerateCucumberErrorReport(CurrentStep));
        }
    }

    public Success Success(Success newSuccess) {

        Substep Step = new Substep();
        Step.Start = newSuccess.StepStart;
        Step.Finish = newSuccess.StepFinish;
        Step.Ex = newSuccess.Ex;
        Step.Passed = newSuccess.bPassed;
        Step.Screenshot = newSuccess.sScreenshot;
        Step.Name = newSuccess.sPath + "." + newSuccess.sAlias + "." + newSuccess.sMethodName + "(" + newSuccess.sMethodArguments + ")";
        Step.MessageAddon = newSuccess.sMessageAddon;
        CurrentStep.Substeps.add(Step);

        if (!Step.Passed) {
            this.bPass = false;

            //String sStopOnError = SharedVariables.Configuration.GetEntryValue("StopOnError");
            //if (!sStopOnError.ToLower().Equals("false")) {
            //    DebugLog.Add("Step failed. Finishing test case...", 1);
            //    Assert.Fail("Step failed. Finishing test case...");
            //}
        }

        return newSuccess;
    }

    @AfterAll
    public void TearDown() {
        if (CurrentStep != null)
            Steps.add(CurrentStep);
        LogConstructor.GenerateLog(this);
    }
}

