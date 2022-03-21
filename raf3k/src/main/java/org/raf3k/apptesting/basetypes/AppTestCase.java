package org.raf3k.apptesting.basetypes;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.raf3k.apptesting.AppReferences;
import org.raf3k.apptesting.AppiumSetup;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.TestCaseBase;

import java.time.Duration;

public class AppTestCase extends TestCaseBase {

    AppiumDriverLocalService service;

    public AppTestCase(String sTestCaseName, String sTestCaseCode, String sTestCaseAuthor) {
        super(sTestCaseName, sTestCaseCode, sTestCaseAuthor);
        setupAppiumLocalService();
    }

    public AppTestCase() {
        super();
        setupAppiumLocalService();
    }

    private void setupAppiumLocalService() {
        try {

            if (SharedVariables.configuration.getProperty("ipAddress") != null)
                    service = new AppiumServiceBuilder().withArgument(GeneralServerFlag.RELAXED_SECURITY)
                            .withIPAddress(SharedVariables.configuration.getProperty(("ipAddress")))
                            .usingPort(Integer.parseInt(SharedVariables.configuration.getProperty("appiumServerPort")))
                            .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
                            .build();
                else
                    service = new AppiumServiceBuilder().withArgument(GeneralServerFlag.RELAXED_SECURITY)
                            .usingAnyFreePort()
                            .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
                            .build();
            AppiumSetup setup = new AppiumSetup();
            setup.setupAppiumDriver(service);
            DebugLog.add("Driver setup complete", 2);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return;
        }
    }

    @AfterAll
    public void appiumTeardown() {
        try {
            if (AppReferences.getAppDriver() != null)
                AppReferences.getAppDriver().quit();
            WebDriverWait wait = new WebDriverWait(AppReferences.getAppDriver(), Duration.ofSeconds(30));
            wait.until((b) -> {
                if (!service.isRunning()) return true;

                return false;
            });
            DebugLog.add("Driver Closed", 2);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return;
        }
    }
}
