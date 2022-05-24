package org.raf3k.apptesting.types;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.raf3k.apptesting.AppReferences;
import org.raf3k.apptesting.basetypes.AppControlBase;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.Success;

public class MbEdit extends AppControlBase {

    public MbEdit(String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }

    public MbEdit(AppControlBase parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    public MbEdit(WebElement parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    /**
     * Method terminates current application.
     *
     * @param bVerifyCleared If set to 'false' action will not be verified.
     * @return Success object.
     */
    public Success clear(boolean bVerifyCleared) {
        return AppReferences.eval().evaluate(() -> {

            control().clear();

            if (bVerifyCleared)
                if (!getControlText().isBlank())
                    throw new RuntimeException(String.format("Control {0} is not cleared.", sAlias));

        }, this, "");
    }

    /**
     * Method sets the text inside the textbox.
     *
     * @param sText               Text to be set.
     * @param bSetWithTouchAction If set to 'true' text will be set with touch action.
     * @param bClickControl       If set to 'true' web control will be clicked on.
     * @param bCleanAll           If set to 'true' control will be cleaned before entering text.
     * @return Success object.
     */
    public Success setText(String sText, boolean bSetWithTouchAction, boolean bClickControl, boolean bCleanAll) {
        return AppReferences.eval().evaluate(() -> {

            if (bClickControl)
                control().click();

            if (bCleanAll)
                control().clear();

            if (!SharedVariables.configuration.getProperty("platformName").toLowerCase().equals("ios")) {
                if (bSetWithTouchAction) {
                    TouchActions act = new TouchActions(AppReferences.getAppDriver());

                    act.sendKeys(control(), sText).build().perform();

                    AppReferences.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
                }
            } else
                control().sendKeys(sText);

        }, this, "");
    }

    /**
     * Method sets the text inside the textbox.
     *
     * @param sText Text to be set.
     * @return Success object.
     */
    public Success setText(String sText) {
        return AppReferences.eval().evaluate(() -> {

            control().sendKeys(sText);

        }, this, "");
    }

    /**
     * Method sets the text inside the textbox.
     *
     * @param sText        Text to be set.
     * @param hideKeyboard Set to 'true' if you want to hide the keyboard after entering text if keyboard shows up.
     * @return Success object.
     */
    public Success setText(String sText, boolean hideKeyboard) {
        return AppReferences.eval().evaluate(() -> {

            if (!SharedVariables.configuration.getProperty("platformName").toLowerCase().equals("ios")) {

                control().sendKeys(sText);

                if (hideKeyboard)
                    AppReferences.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
            } else {
                control().sendKeys(sText);
                if (hideKeyboard)
                    AppReferences.getIOSDriver().hideKeyboard();
            }
        }, this, "");
    }

    /**
     * Method verifies if textbox is empty or not empty.
     *
     * @param bEmpty Set to 'false' if you want to check if textbox is not empty and vice versa.
     * @return Success object.
     */
    public Success verifyEmpty(boolean bEmpty) {
        return AppReferences.eval().evaluate(() -> {

            String sControlValue = control().getText();

            if (bEmpty && !sControlValue.isBlank())
                throw new RuntimeException(String.format("Control {0} text is not empty.", sAlias));

            if (!bEmpty && sControlValue.isBlank())
                throw new RuntimeException(String.format("Control {0} text is empty.", sAlias));

        }, this, "");
    }
}
