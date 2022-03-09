package org.raf3k.apptesting.types;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.ios.IOSDriver;
import org.raf3k.apptesting.AppReferences;
import org.raf3k.apptesting.basetypes.AppControlBase;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.Success;

public class Application extends AppControlBase {

    private String sApplicationPackageName;

    public Application(String sAlias) {
        this.sApplicationPackageName = AppReferences.sAppPackageName;
        this.sAlias = sAlias;
    }

    /**
     * Method verifies if application is running in foreground.
     *
     * @return Success object.
     */
    public Success verifyRunning() {
        return AppReferences.eval().evaluate(() -> {

            if (getCurrentAppState() != ApplicationState.RUNNING_IN_FOREGROUND)
                throw new RuntimeException(String.format("Application {0} is not running. Current state is: {1}", sApplicationPackageName, applicationStateToString(getCurrentAppState())));

        }, this, "");
    }

    /**
     * Method returns 'AppState' enum entry as string.
     *
     * @param appState Application state.
     * @return Success object.
     */
    private String applicationStateToString(ApplicationState appState) {
        switch (appState) {
            case NOT_INSTALLED:
                return "Not Installed";
            case NOT_RUNNING:
                return "Not Running";
            case RUNNING_IN_BACKGROUND_SUSPENDED:
                return "Running In Background Or Suspended";
            case RUNNING_IN_BACKGROUND:
                return "Running In Background";
            case RUNNING_IN_FOREGROUND:
                return "Running In Foreground";
            default:
                return "";
        }
    }

    /**
     * Method returns current AppState.
     *
     * @return Current AppState.
     */
    public ApplicationState getCurrentAppState() {

        ApplicationState currentAppState;

        if (SharedVariables.configuration.getProperty("platformName").toLowerCase() == "android") {
            currentAppState = ((AndroidDriver) AppReferences.getAppDriver()).queryAppState(sApplicationPackageName);
        } else {
            currentAppState = ((IOSDriver) AppReferences.getAppDriver()).queryAppState(sApplicationPackageName);
        }

        return currentAppState;
    }

    /**
     * Method verifies current application state.
     *
     * @param appState Expected application state.
     * @return Success object.
     */
    public Success verifyState(ApplicationState appState) {
        return AppReferences.eval().evaluate(() -> {

            if (getCurrentAppState() != appState)
                throw new RuntimeException(String.format("Application {0} state is not as expected." +
                        "Current state: {1}, Expected state: {2}.", sApplicationPackageName, applicationStateToString(getCurrentAppState()), applicationStateToString(appState)));

        }, this, "");
    }

    /**
     * Method closes the current application.
     *
     * @return Success object.
     */
    public Success close() {
        return AppReferences.eval().evaluate(() -> {

            AppReferences.getAppDriver().close();

            if (getCurrentAppState() != ApplicationState.NOT_RUNNING)
                throw new RuntimeException(String.format("Application {0} is not closed.", sApplicationPackageName));

        }, this, "");
    }

    /**
     * Method sends current application to background.
     *
     * @return Success object.
     */
    public Success sendToBackground(){
        return AppReferences.eval().evaluate(() -> {

            if (SharedVariables.configuration.getProperty("platformName").toLowerCase() == "android") {
                AppReferences.getAndroidDriver().runAppInBackground(null);
            } else {
                AppReferences.getIOSDriver().runAppInBackground(null);
            }

        }, this, "");
    }

    /**
     * Method opens current application.
     *
     * @return Success object.
     */
    public Success open(){
        return AppReferences.eval().evaluate(() -> {

            if (SharedVariables.configuration.getProperty("platformName").toLowerCase() == "android") {
                AppReferences.getAndroidDriver().activateApp(sApplicationPackageName);
            } else {
                AppReferences.getIOSDriver().activateApp(sApplicationPackageName);
            }

        }, this, "");
    }

    /**
     * Method resets current application.
     *
     * @return Success object.
     */
    public Success reset(){
        return AppReferences.eval().evaluate(() -> {

            if (SharedVariables.configuration.getProperty("platformName").toLowerCase() == "android") {
                AppReferences.getAndroidDriver().resetApp();
            } else {
                AppReferences.getIOSDriver().resetApp();
            }

        }, this, "");
    }

    /**
     * Method terminates current application.
     *
     * @return Success object.
     */
    public Success terminate(){
        return AppReferences.eval().evaluate(() -> {

            if (SharedVariables.configuration.getProperty("platformName").toLowerCase() == "android") {
                AppReferences.getAndroidDriver().terminateApp(sApplicationPackageName);
            } else {
                AppReferences.getIOSDriver().terminateApp(sApplicationPackageName);
            }

        }, this, "");
    }
}
