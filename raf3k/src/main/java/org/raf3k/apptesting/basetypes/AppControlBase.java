package org.raf3k.apptesting.basetypes;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.raf3k.apptesting.AppReferences;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.logging.Success;


public class AppControlBase extends ControlObject {

    public boolean bDisplayed = true;
    public boolean bMustBeVisible = true;
    public boolean bIsIos = SharedVariables.configuration.getProperty("platformName").toLowerCase() == "ios";
    public int iTimeoutOverride = -1;
    private By searchBy;
    private AppControlBase parent;
    private WebElement _ControlReference;

    public WebElement control() {
        try {
            String sError = "";
            if (_ControlReference != null) return _ControlReference;
            return AppReferences.appEng().findControl(sControlType, sPath, searchBy, parent, sError, bDisplayed, bMustBeVisible, iTimeoutOverride);
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }

    public AppControlBase() {
        this.sControlType = this.getClass().getSimpleName();
        this.sPath = AppReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
    }

    public AppControlBase(WebElement control) {
        this.sControlType = this.getClass().getName();
        this.sPath = AppReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());

        _ControlReference = control;
    }

    public AppControlBase(String sAlias, By searchByAndroid, By searchByiOS) {
        this.sControlType = this.getClass().getName();

        switch (SharedVariables.configuration.getProperty("platformName").toLowerCase()) {
            case "ios":
                this.searchBy = searchByiOS;
                break;
            default:
                this.searchBy = searchByAndroid;
                break;
        }
        this.sAlias = sAlias;
        this.sPath = AppReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
    }

    public AppControlBase(AppControlBase parent, String sAlias, By searchByAndroid, By searchByiOS) {
        this.sControlType = this.getClass().getName();

        switch (SharedVariables.configuration.getProperty("platformName").toLowerCase()) {
            case "ios":
                this.searchBy = searchByiOS;
                break;
            default:
                this.searchBy = searchByAndroid;
                break;
        }
        this.sAlias = sAlias;
        this.sPath = AppReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
        this.parent = parent;
    }

    public AppControlBase(WebElement parent, String sAlias, By searchByAndroid, By searchByiOS) {
        this.sControlType = this.getClass().getName();

        switch (SharedVariables.configuration.getProperty("platformName").toLowerCase()) {
            case "ios":
                this.searchBy = searchByiOS;
                break;
            default:
                this.searchBy = searchByAndroid;
                break;
        }
        this.sAlias = sAlias;
        this.sPath = AppReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());

        this.parent = new AppControlBase();
        this.parent._ControlReference = parent;
        this.parent.sControlType = "inner";
        this.parent.sAlias = "Control parent";
        this.parent.sPath = this.sPath + ".Parent";
    }

    //region Methods
    private Boolean checkIfControlStale() {
        try {
            if (_ControlReference != null) {
                if (_ControlReference.isDisplayed())
                    return false;
            }

            return true;

        } catch (Exception ex) {
            return true;
        }
    }

    /**
     * Method verifies control text.
     *
     * @param sText   Control text that needs to be verified.
     * @param bExists If set to 'true', checks if text exists. If set to 'false', checks if text does not exist.
     * @return Success object.
     */
    public Success verifyText(String sText, boolean bExists) {
        return AppReferences.eval().evaluate(() -> {

            String sControlText = getControlText();

            if (bExists && !sControlText.equals(sText))
                throw new RuntimeException(String.format("Text is not as expected. Expected text: {0}. Control text: {1}.", sText, sControlText));

            if (!bExists && sControlText.equals(sText))
                throw new RuntimeException(String.format("Text {0} exists but not expected to exist.", sText));

        }, this, "");
    }

    /**
     * Method verifies if the text from WebLabel contains the given text.
     *
     * @param sText   Expected string that label text contains.
     * @param bExists If set to 'true', checks if text exists. If set to 'false', checks if text does not exist.
     * @return Success object.
     */
    public Success verifyTextContains(String sText, boolean bExists) {
        return AppReferences.eval().evaluate(() -> {

            String sControlText = getControlText();

            if (bExists && !sControlText.equals(sText))
                throw new RuntimeException(String.format("Label text is not as expected. Expected text: {0}. Control text: {1}", sText, sControlText));

            if (!bExists && sControlText.equals(sText))
                throw new RuntimeException(String.format("Text {0} exists but not expected to exist.", sText));

        }, this, "");
    }

    /**
     * Method gets the control text.
     * If textbox type is 'password' it will return empty string.
     *
     * @return Web control value.
     */
    public String getControlText() {
        try {
            if (SharedVariables.configuration.getProperty("platformName").toLowerCase() != "ios")
                if (!control().getAttribute("password").toLowerCase().equals("false"))
                    return "";

            return control().getText();

        } catch (Exception ex) {
            DebugLog.add(ex);
            return "";
        }
    }

    /**
     * Method checks if web control is displayed or not.
     *
     * @return 'True' if web control is displayed, 'false' if not displayed.
     */
    public boolean isDisplayed() {
        if (control() == null)
            return false;

        return control().isDisplayed();
    }

    /**
     * Method checks if web control is enabled or not.
     *
     * @return 'True' if web control is enabled, 'false' if not enabled.
     */
    public boolean isEnabled() {
        if (control().isEnabled() && control().isDisplayed())
            return true;
        else
            return false;
    }

    /**
     * Method clicks on web control.
     *
     * @return Success object.
     */
    public Success click() {
        return AppReferences.eval().evaluate(() -> {

            control().click();

        }, this, "");
    }

    /**
     * Method taps on web control.
     *
     * @return Success object.
     */
    public Success tap() {
        return AppReferences.eval().evaluate(() -> {

            AppReferences.touchActionsBuilder().singleTap(control()).perform();

        }, this, "");
    }

    /**
     * Method verifies if web control exists.
     *
     * @param bExists Set to 'false' if you want to check that it does not exist and vice versa.
     * @return Success object.
     */
    public Success exists(boolean bExists) {
        return AppReferences.eval().evaluate(() -> {

            if (bExists) {
                if (control() == null)
                    throw new RuntimeException(String.format("Web control {0} does not exist.", sAlias));
            } else {
                if (control() != null)
                    throw new RuntimeException(String.format("Web control {0} exists.", sAlias));
            }
        }, this, "");
    }

    /**
     * Method verifies if the web control is displayed.
     *
     * @param bDisplayed Set to 'false' if you want to check if the web control is not displayed and vice versa.
     * @return Success object.
     */
    public Success displayed(boolean bDisplayed) {
        return AppReferences.eval().evaluate(() -> {

            if (bDisplayed && !control().isDisplayed())
                throw new RuntimeException(String.format("Web control {0} is not displayed.", sAlias));

            if (!bDisplayed && control().isDisplayed())
                throw new RuntimeException(String.format("Web control {0} is displayed.", sAlias));

        }, this, "");
    }

    /**
     * Method verifies if web control is enabled.
     *
     * @param bEnabled Set to 'false' if you want to check if web control is not enabled and vice versa.
     * @return Success object.
     */
    public Success enabled(boolean bEnabled) {
        return AppReferences.eval().evaluate(() -> {

            if (!bEnabled && control().isEnabled())
                throw new RuntimeException("Control is enabled.");

            if (bEnabled) {
                if (!control().isEnabled())
                    throw new RuntimeException("Control is disabled.");

                if (!control().isDisplayed())
                    throw new RuntimeException("Control is not displayed.");
            }

        }, this, "");
    }

    /**
     * Method verifies the size of the web control.
     *
     * @param iWidth  Expected width of web control.
     * @param iHeight Expected height of web control.
     * @return Success object.
     */
    public Success verifySize(int iWidth, int iHeight) {
        return AppReferences.eval().evaluate(() -> {

            Dimension size = control().getSize();

            if (size.height != iHeight)
                throw new RuntimeException(String.format("Height is not as expected. Expected height is: {0} but height is {1}", iHeight, size.height));

            if (size.width != iWidth)
                throw new RuntimeException(String.format("Width is not as expected. Expected width is: {0} but width is {1}", iWidth, size.width));

        }, this, "");
    }

    /**
     * Method verifies the position of the web control.
     *
     * @param iXAxis Expected X axis of web control.
     * @param iYAxis Expected Y axis of web control.
     * @return Success object.
     */
    public Success verifyPosition(int iXAxis, int iYAxis) {
        return AppReferences.eval().evaluate(() -> {

            Point location = control().getLocation();

            if (location.x != iXAxis)
                throw new RuntimeException(String.format("X axis is not as expected. Expected X axis is: {0} but X axis is {1}", iXAxis, location.x));

            if (location.y != iYAxis)
                throw new RuntimeException(String.format("Y axis is not as expected. Expected Y axis is: {0} but Y axis is {1}", iYAxis, location.y));

        }, this, "");
    }

    /**
     * Method verifies if element attribute has specific value.
     *
     * @param sAttribute      Expected element attribute.
     * @param sAttributeValue Expected element attribute value.
     * @return Success object.
     */
    public Success verifyAttributeValue(String sAttribute, String sAttributeValue) {
        return AppReferences.eval().evaluate(() -> {
            String sSelected = "";

            if (sAttribute == "checked") {
                if (SharedVariables.configuration.getProperty("platformName").toLowerCase() == "ios") {
                    sSelected = "selected";
                }
            }

            String sControlAttribute;

            if (!sSelected.isBlank()) {
                sControlAttribute = control().getAttribute(sSelected);
            } else {
                sControlAttribute = control().getAttribute(sAttribute);
            }

            if (sControlAttribute == null)
                throw new RuntimeException(String.format("Element does not contain attribute {0}.", sAttribute));

            if (sControlAttribute != sAttributeValue)
                throw new RuntimeException(String.format("Attribute {0} does not contain value {1}. Attribute value: {2}", sAttribute, sAttributeValue, sControlAttribute));
        }, this, "");
    }
    //endregion
}
