package RAF3kShared.Logging;

import RAF3kShared.Logging.Evaluator;
import RAF3kShared.Logging.Step;

import java.util.ArrayList;

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
        //RAFtestCaseAttribute Attribuite = this.GetType().GetCustomAttributes(true)[0] as RAFtestCaseAttribute;
        //if (Attribuite != null)
        //{
        //    sTestCaseName = Attribuite._TestCaseName;
        //    sTestCaseCode = Attribuite._TestCaseCode;
        //    sTestCaseAuthor = Attribuite._Author;
        //}
        //else
        //{
        sTestCaseName = "Not Defined";
        sTestCaseCode = "Not Defined";
        sTestCaseAuthor = "Not Defined";
        System.out.println("Warning *** No RAFtestCase attribute found, log data might be inconclusive");
        //}
        //String path = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "Settings", @"Settings.xml");
        //SharedVariables.Configuration = new ConfigurationHelper(path);
        //SharedVariables.TestData = new TestDataHelper();
    }

    public TestCaseBase(String TestCaseName, String TestCaseCode, String TestCaseAuthor) {

        sTestCaseName = TestCaseName;
        sTestCaseCode = TestCaseCode;
        sTestCaseAuthor = TestCaseAuthor;

        //string path = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "Settings", @"Settings.xml");
        //SharedVariables.Configuration = new ConfigurationHelper(path);
        //SharedVariables.TestData = new TestDataHelper();
    }

    public void NewStep(int iNumber, String sName) {
        if (CurrentStep != null)
            Steps.add(CurrentStep);

        CurrentStep = new Step();
        CurrentStep.StepNumber = iNumber;
        CurrentStep.StepName = sName;
        CurrentStep.Substeps = new ArrayList<Substep>();
        //DebugLog.Add($"Added new step: {iNumber.ToString()} : {sName}", 2);
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

    //change type from Success to void
    public void Success() {
        Substep Step = new Substep();
        //Step.Start = value.StepStart;
        //Step.Finish = value.StepFinish;
        //Step.Ex = value.Ex;
        //Step.Passed = value.bPassed;
        //Step.Screenshot = value.sScreenshot;
        //Step.Name = $ "{value.sPath}.{value.sAlias}.{value.sMethodName}({value.sMethodArguments})";
        //Step.MessageAddon = value.sMessageAddon;
        CurrentStep.Substeps.add(Step);

        if (!Step.Passed) {
            this.bPass = false;

            //String sStopOnError = SharedVariables.Configuration.GetEntryValue("StopOnError");
            //if (!sStopOnError.ToLower().Equals("false")) {
            //    DebugLog.Add("Step failed. Finishing test case...", 1);
            //    Assert.Fail("Step failed. Finishing test case...");
            //}
        }
    }

    //[OneTimeTearDown]
    public void TearDown()
    {
        if (CurrentStep != null)
            Steps.add(CurrentStep);
        //LogConstructor.GenerateLog(this);
    }
}

