package Hooks;

import ExtendedTypes.API.RAFRestResponseEx;
import Settings.GlobalParameters;
import Shared.XrayShared;
import Testbook.Cucumber.Glue.UI.HTInfonovaSteps;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeOptions;
import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.raf3k.shared.ZephyrScaleAPICalls;
import org.raf3k.shared.logging.LogConstructor;
import org.raf3k.shared.logging.TestCaseBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static Hooks.HTInfonovaHooks.Logout;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Hooks {
    public static String testStartTime;
    public static String featureName;
    public static String altAccessToken;
    public static RAFRestResponseEx rafRestResponseEx;
    public static Collection<String> tags;
    private static UITestCase uiTestCase;
    private static APITestCase apiTestCase;
    private int currentStepDefIndex = 0;
    private List<PickleStepTestStep> listStepsText = null;
    public static java.util.Map<String, Object> savedValues;
    private static String userDir = System.getProperty("user.dir");
    private static String userHome = System.getProperty("user.home");
    private static String sourceDirectory = userDir + File.separator + "src" + File.separator + "main" + File.separator + "Tests" + File.separator + "Testbook" + File.separator + "Cucumber" + File.separator + "Features" + File.separator + "UI";
    private static String zipFilePath = userHome + File.separator + "Postman" + File.separator + "files" + File.separator + "Features.zip";

    public class ExecutionManager {

        public static List<TestCaseBase> executedTestCases = new ArrayList<>();

        public static void addExecutedTestCase(TestCaseBase testCase) {
            executedTestCases.add(testCase);
        }

        private static boolean importExecuted = false;

        public static void importExecutionResults(List<TestCaseBase> testCases) {
            if (!importExecuted) {
                XrayShared.importExecutionResults(testCases);
                importExecuted = true;
            }
        }
    }

    @AfterAll
    public static void afterTestExecution() {
        //XrayShared.zipFeatureFiles(sourceDirectory, zipFilePath);
        //ExecutionManager.importExecutionResults(executedTestCases);
        if (uiTestCase != null)
            uiTestCase.uiTeardown();
        CopyOutput();
    }

    private static void CopyOutput() {
        File DestinationFile = null;
        File resultsFile = null;
        try {
            java.net.URI Results = Paths.get("target", "/surefire-reports/Testbook.xml").toUri();
            java.net.URI Destination = Paths.get("target", "/surefire-reports/" + uiTestCase.sTestCaseCode + ".xml").toUri();
            resultsFile = new File(Results);
            DestinationFile = new File(Destination);
            FileUtils.copyFile(resultsFile, DestinationFile);
        } catch (Exception e) {
            return;
        }
    }

    private static String getFeatureFileNameFromScenarioId(Scenario scenario) {
        String[] tab = scenario.getUri().toString().split("/");
        int rawFeatureNameLength = tab.length;
        String featureName = tab[rawFeatureNameLength - 1].split("\\.")[0];

        return featureName;
    }

    @Before("@UI")
    public void beforeEachUIScenario(Scenario scenario) throws Exception {
        String testCaseName = scenario.getName() != null ? scenario.getName() : "Not defined";
        String testCaseCode = findTagValue(scenario, "TestCaseCode");
        String testCaseAuthor = findTagValue(scenario, "Author");
        testStartTime = GetCalendar.getCurrentTime("HH:mm:ss");
        featureName = getFeatureFileNameFromScenarioId(scenario);
        savedValues = new HashMap<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        uiTestCase = new UITestCase(testCaseName, testCaseCode, testCaseAuthor, options);

        GlobalParameters.testCaseBase = uiTestCase;
        currentStepDefIndex = 0;
        listStepsText = getStepsText(scenario);
    }

    @Before("@API")
    public void beforeEachAPIScenario(Scenario scenario) throws Exception {
        tags = scenario.getSourceTagNames();
        String testCaseName = scenario.getName() != null ? scenario.getName() : "Not defined";
        String testCaseCode = findTagValue(scenario, "TestCaseCode");
        String testCaseAuthor = findTagValue(scenario, "Author");
        savedValues = new HashMap<>();

        apiTestCase = new APITestCase(testCaseName, testCaseCode, testCaseAuthor);
        GlobalParameters.testCaseBase = apiTestCase;

        currentStepDefIndex = 0;
        listStepsText = getStepsText(scenario);
    }

    public void findApiTag() {
        Optional<String> tagOptional = tags.stream()
                .filter(x -> x.contains("JiraRef"))
                .findFirst();

        String tag = tagOptional.orElse("");

        if (tag != null && !tag.isEmpty()) {
            ZephyrScaleAPICalls.POSTTestResults("testexecutions", tag.substring(tag.indexOf(":") + 1), apiTestCase.bPass);
        }
    }

    public static void findUITag() {
        Optional<String> tagOptional = tags.stream()
                .filter(x -> x.contains("JiraRef"))
                .findFirst();

        String tag = tagOptional.orElse("");

        if (tag != null && !tag.isEmpty()) {
            ZephyrScaleAPICalls.POSTTestResults("testexecutions", tag.substring(tag.indexOf(":") + 1), uiTestCase.bPass);
        }
    }

    @After("@API")
    public void afterEachScenarioAPI(Scenario scenario) throws IOException {
        findApiTag();
        LogConstructor.generateLog(apiTestCase);
    }

    @After("@UI")
    public static void afterEachScenario(Scenario scenario) throws IOException {
        XrayShared.generateHTMLLog(uiTestCase);
        ExecutionManager.addExecutedTestCase(uiTestCase);
        HTInfonovaSteps.iSelectOptionInProfileListOnRightPartOfThePage("Logout");
        if (Logout) {
            HTInfonovaSteps.iShouldSeeThePopUpWindow();
            HTInfonovaSteps.iClickOnButtonInPopUpWindow("leave");
        }
        uiTestCase.recorder.stop();
    }

    @BeforeStep("@API")
    public void beforeStepAPI() {
        PickleStepTestStep stepDefs = listStepsText.get(currentStepDefIndex);
        currentStepDefIndex++;

        apiTestCase.addBDDStep(stepDefs.getStep().getText());
    }

    @BeforeStep("@UI")
    public void beforeStep() {
        PickleStepTestStep stepDefs = listStepsText.get(currentStepDefIndex);
        currentStepDefIndex++;

        uiTestCase.addBDDStep(stepDefs.getStep().getText());
    }

    @AfterStep("@API")
    public void afterStepAPI() {
        apiTestCase.finishBDDStep();
    }

    @AfterStep("@UI")
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

    public class GetCalendar {
        public static java.util.Date getTime() {
            Calendar c = Calendar.getInstance();
            return c.getTime();
        }

        public static String getCurrentTime(String sFormat) {
            return new SimpleDateFormat(sFormat).format(getTime());
        }
    }
}