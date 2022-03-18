package cucumber.hooks;

import cucumber.maps.Map;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.raf3k.shared.logging.LogConstructor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class TestHooks {
    private static UITestCase uiTestCase;
    private int currentStepDefIndex = 0;
    private List<PickleStepTestStep> listStepsText = null;

    @Before
    public void beforeEachScenario(Scenario scenario) throws Exception {
        String testCaseName = scenario.getName() != null ? scenario.getName() : "Not defined";
        String testCaseCode = findTagValue(scenario, "TestCaseCode");
        String testCaseAuthor = findTagValue(scenario, "Author");

        uiTestCase = new UITestCase(testCaseName, testCaseCode, testCaseAuthor);
        Map.initialize();

        UIReferences.uiTestCase = uiTestCase;
        currentStepDefIndex = 0;
        listStepsText = getStepsText(scenario);
    }

    @After
    public void afterEachScenario() {
        LogConstructor.generateLog(uiTestCase);
    }

    @BeforeStep
    public void beforeStep() {
        PickleStepTestStep stepDefs = listStepsText.get(currentStepDefIndex);
        currentStepDefIndex++;

        uiTestCase.addBDDStep(stepDefs.getStep().getText());
    }

    @AfterStep
    public void afterStep() {
        uiTestCase.finishBDDStep();
    }

    private String findTagValue(Scenario scenario, String tagName) {
        String value = "Not Defined";
        String fullTag = scenario.getSourceTagNames().stream().filter(m -> m.toLowerCase().contains(tagName.toLowerCase())).findFirst().orElse(null);
        if (fullTag != null && !fullTag.isEmpty() && fullTag.contains(":") && fullTag.length() > fullTag.indexOf(":") + 1)
            value = fullTag.substring(fullTag.indexOf(":") + 1);

        return value;
    }

    private List<PickleStepTestStep> getStepsText(Scenario scenario) throws Exception {
        Field f = scenario.getClass().getDeclaredField("delegate");
        f.setAccessible(true);
        io.cucumber.core.backend.TestCaseState sc = (io.cucumber.core.backend.TestCaseState) f.get(scenario);
        Field f1 = sc.getClass().getDeclaredField("testCase");
        f1.setAccessible(true);
        io.cucumber.plugin.event.TestCase testCase = (io.cucumber.plugin.event.TestCase) f1.get(sc);

        List<PickleStepTestStep> testSteps = testCase.getTestSteps().stream()
                .filter(x -> x instanceof PickleStepTestStep).map(x -> (PickleStepTestStep) x).collect(Collectors.toList());

        return testSteps;
    }
}
