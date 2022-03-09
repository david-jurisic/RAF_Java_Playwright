package org.raf3k.apptesting;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;

import java.net.URL;

public class AppiumSetup {
    public void setupAppiumDriver(AppiumDriverLocalService service) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String sPlatformName = SharedVariables.configuration.getProperty("platformName");
        if (sPlatformName != null)
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, sPlatformName);

        String sDeviceName = SharedVariables.configuration.getProperty("deviceName");
        if (sDeviceName != null)
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, sDeviceName);

        String sAutomationName = SharedVariables.configuration.getProperty("automationName");
        if (sAutomationName != null)
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, sAutomationName);

        String sBundleId = SharedVariables.configuration.getProperty("bundleId");
        if (sBundleId != null)
            capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, sBundleId);

        String sAppPath = SharedVariables.configuration.getProperty("appPath");
        if (sAppPath != null)
            capabilities.setCapability(MobileCapabilityType.APP, sAppPath);

        String sAppPackage = SharedVariables.configuration.getProperty("appPackage");
        if (sAppPackage != null)
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, sAppPackage);

        String sAppActivity = SharedVariables.configuration.getProperty("appActivity");
        if (sAppActivity != null)
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, sAppActivity);

        String sNewCommandTimeout = SharedVariables.configuration.getProperty("controlWaitTime");
        if (sNewCommandTimeout != null)
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, sNewCommandTimeout);

        try {
            switch (sPlatformName.toLowerCase()) {
                case "android":
                    AppReferences.setAppDriver(new AndroidDriver(service, capabilities));
                    break;

                case "ios":
                    AppReferences.setAppDriver(new IOSDriver(service, capabilities));
                    break;

                default:
                    AppReferences.setAppDriver(new AndroidDriver(service, capabilities));
                    break;

            }
        } catch (Exception ex) {
            DebugLog.add(ex);
        }
    }
}
