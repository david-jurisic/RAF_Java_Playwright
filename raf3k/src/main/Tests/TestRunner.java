import Hooks.Hooks;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Hooks.Hooks.ExecutionManager.executedTestCases;

@CucumberOptions(
        plugin = {"pretty", "json:target/surefire-reports/Testbook.json", "junit:target/surefire-reports/Testbook.xml"},
        features = "src/main/Tests/Testbook/Cucumber/Features",
        glue = {"Hooks", "Testbook.Cucumber.Glue"})
public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        Hooks.ExecutionManager.importExecutionResults(executedTestCases);
        testNGCucumberRunner.finish();
    }
}