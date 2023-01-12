package org.raf3k.guittesting.webtesting.basetypes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.SeleniumSetup;
import org.raf3k.guittesting.webtesting.recorder.TestRecorder;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.TestCaseBase;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UITestCase extends TestCaseBase {
    public ScreenRecorder recorder;
    private Boolean recordingEnabled = Boolean.parseBoolean(SharedVariables.configuration.getProperty("recordingEnabled"));

    public UITestCase(String testCaseName, String testCaseCode, String testCaseAuthor) {
        super(testCaseName, testCaseCode, testCaseAuthor);
        setupWebDriver(null);

        if (recordingEnabled)
            startScreenRecording(testCaseCode);
    }

    public UITestCase(String testCaseName, String testCaseCode, String testCaseAuthor, AbstractDriverOptions options) {
        super(testCaseName, testCaseCode, testCaseAuthor);
        setupWebDriver(options);

        if (recordingEnabled)
            startScreenRecording(testCaseCode);
    }

    public UITestCase() {
        super();
        setupWebDriver(null);
    }

    //region Methods
    private void setupWebDriver(AbstractDriverOptions driverOptions) {
        try {
            if (UIReferences.getWebDriver() == null) {
                SeleniumSetup setup = new SeleniumSetup();
                if (driverOptions == null)
                    setup.setupWebDriver(null, null);
                else
                    setup.setupWebDriver(driverOptions, null);

                DebugLog.add("Driver setup complete", 2);
            } else {
                DebugLog.add("Driver already running", 2);
            }

            DebugLog.add("Running test: " + this.toString(), 2);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return;
        }
    }

    private void startScreenRecording(String folderName) {
        try {
            recorder = new TestRecorder(new File(SharedVariables.configuration.getProperty("logFilePath") + "\\" + folderName));
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region Junit Teardown/Setup
    @AfterAll
    public void uiTeardown() {
        try {
            if (recordingEnabled)
                recorder.stop();

            if (UIReferences.getWebDriver() != null) {
                UIReferences.getWebDriver().quit();
                UIReferences.setWebDriver(null);
            }

            DebugLog.add("Driver Closed", 2);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return;
        }
    }
    //endregion
}
