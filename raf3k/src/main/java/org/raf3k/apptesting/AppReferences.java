package org.raf3k.apptesting;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.raf3k.apptesting.basetypes.AppTestCase;
import org.raf3k.shared.Helpers;
import org.raf3k.shared.logging.Evaluator;

public class AppReferences {

    public static String sAppPackageName;
    private static AppiumDriver _AppDriver;

    public static AppiumDriver getAppDriver() {
        return _AppDriver;
    }

    public static AndroidDriver getAndroidDriver() {
        return (AndroidDriver) _AppDriver;
    }

    public static IOSDriver getIOSDriver() {
        return (IOSDriver) _AppDriver;
    }

    public static void setAppDriver(AppiumDriver driver) {
        _AppDriver = driver;
    }

    private static AppEngine _AppEng;

    public static AppEngine appEng() {
        if (_AppEng == null)
            _AppEng = new AppEngine();
        return _AppEng;
    }

    private static Evaluator _Eval;

    public static Evaluator eval() {
        if (_Eval == null)
            _Eval = new Evaluator(getAppDriver());
        if (_Eval.driver == null)
            _Eval = new Evaluator(getAppDriver());
        return _Eval;
    }

    private static Helpers _Hlpr;

    public static Helpers hlpr() {

        if (_Hlpr == null)
            _Hlpr = new Helpers();
        return _Hlpr;
    }

    private static TouchActions _TouchActionsBuilder;

    public static TouchActions touchActionsBuilder() {
        if (_TouchActionsBuilder == null)
            _TouchActionsBuilder = new TouchActions(getAppDriver());
        return _TouchActionsBuilder;
    }

    public static AppTestCase AppTestCase;
}
