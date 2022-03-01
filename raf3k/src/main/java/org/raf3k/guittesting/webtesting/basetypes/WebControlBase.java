package org.raf3k.guittesting.webtesting.basetypes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.raf3k.shared.DebugLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.shared.ControlObject;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.logging.Success;

import javax.management.RuntimeMBeanException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WebControlBase extends ControlObject {
    public Boolean bDisplayed = true;
    public Boolean bMustBeVisible = true;
    public int iTimeoutOverride = -1;
    private WebElement _Controlreference;
    private By searchBy;
    private WebControlBase parent;
    public Actions actionsBuilder;

    public WebElement control() {
        try {
            String sError = "";
            waitForLoaders();
            if (checkIfControlStale())
                _Controlreference = UIReferences.getWebEng().findControl(sControlType, sPath, searchBy, parent, sError, bDisplayed, bMustBeVisible, iTimeoutOverride);
            return _Controlreference;
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }

    public WebControlBase() {
        this.sControlType = this.getClass().getSimpleName();
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
    }

    public WebControlBase(By searchBy, String alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias = alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
    }

    public WebControlBase(By searchBy, WebControlBase parent, String alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias = alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());
        this.parent = parent;
    }

    public WebControlBase(WebElement control, String alias) {
        this.sControlType = this.getClass().getName();
        this.sAlias = alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());

        _Controlreference = control;
    }

    public WebControlBase(By searchBy, WebElement Parent, String Alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias = Alias;
        this.sPath = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[4].getClassName());

        this.parent = new WebControlBase();
        this.parent._Controlreference = Parent;
        this.parent.sControlType = "inner";
        this.parent.sAlias = "Control parent";
        this.parent.sPath = this.sPath + ".Parent";
    }

    private Boolean checkIfControlStale() {
        try {
            if (_Controlreference != null) {
                if (_Controlreference.isDisplayed())
                    return false;
            }
            return true;
        } catch (Exception ex) {
            return true;
        }
    }

    private void waitForLoaders() {
        UIReferences.getWebEng().waitForPageLoading();
        //UIReferences.WebEng.WaitForLoaders();
    }

    /**
     * Method retrieves web control value.If textbox type is 'password' it will return empty string.
     *
     * @return Web control value
     */
    public String getControlText() {
        if (control().getAttribute("type") != null)
            if (!control().getAttribute("type").equals("password"))
                return "";

        if (control().getAttribute("value") != null)
            return control().getAttribute("value").toString();
        else
            return control().getText();
    }

    /**
     * Method clicks on web control.
     *
     * @return Success object
     */
    public Success click() {
        return UIReferences.eval().evaluate(() ->
        {
            this.control().click();
        }, this, "");
    }

    /**
     * Method verifies the web control is displayed.
     *
     * @param bDisplayed Bool parameter, if set to false, verifies the web control is not displayed.
     * @return Success object
     */
    public Success displayed(Boolean bDisplayed) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bDisplayed && !control().isDisplayed())
                throw new RuntimeException(MessageFormat.format("Web control {0} is not displayed.", sAlias));
            if (!bDisplayed && control().isDisplayed())
                throw new RuntimeException(MessageFormat.format("Web control {0} is displayed.", sAlias));
        }, this, "");
    }

    /**
     * Method verifies web control is enabled.
     *
     * @param bEnabled     Set to 'false' if you want to check if web control is not enabled. Set to true if you want to check if web control is enabled
     * @param iControlWait Int of time to wait for the control. In seconds. Default is 2 if you send null
     * @return Success object
     */
    public Success enabled(Boolean bEnabled, Integer iControlWait) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists(true);

            if (!bEnabled && control().isEnabled()) {
                throw new RuntimeException("Control is enabled.");
            }

            if (bEnabled && !control().isEnabled()) {
                throw new RuntimeException("Control is not enabled");
            }

        }, this, "");
    }

    /**
     * Method verifies if web control exists.
     *
     * @param bExists Set to 'false' if you want to check if web control does not exist. It is 'true' by default.
     * @return Success object.
     */
    public Success exists(Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {

            if (bExists) {
                if (control() == null)
                    throw new RuntimeException(MessageFormat.format("Web control {0} does not exists.", sAlias));
            } else {
                if (control() != null)
                    throw new RuntimeException(MessageFormat.format("Web control {0} exists.", sAlias));
            }

        }, this, "");
    }

    /**
     * Method verifies if element has given attribute.
     *
     * @param sAttribute Expected element attribute.
     * @param bExists    If true, checks if attribute exists, if false, check if attribute does not exist.
     * @return Success object.
     */
    public Success verifyAttributeExists(String sAttribute, Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists(true);
            var attributes = getAllControlAttributes();
            var attributesKeysJoined = attributes.keySet().stream()
                    .map(o -> o + ", ").collect(Collectors.joining());

            if (bExists && !attributes.containsKey(sAttribute))
                throw new RuntimeException(MessageFormat.format("Element does not contain attribute {0}. Element attributes: {1}.", sAttribute, attributesKeysJoined));
            if (!bExists && attributes.containsKey(sAttribute))
                throw new RuntimeException(MessageFormat.format("Element contains attribute {0}. Element attributes: {1}.", sAttribute, attributesKeysJoined));
        }, this, "");
    }

    /**
     * Method verifies if element attribute has value.
     *
     * @param sAttribute      Expected element attribute.
     * @param sAttributeValue Expected element attribute value.
     * @return Success object.
     */
    public Success verifyAttributeValue(String sAttribute, String sAttributeValue) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists(true);
            var attributes = getAllControlAttributes();
            var attributesKeysJoined = attributes.keySet().stream()
                    .map(o -> o + ", ").collect(Collectors.joining());
            var attributesValuesJoined = attributes.values().stream()
                    .map(o -> o.toString() + ", ").collect(Collectors.joining());

            if (!attributes.containsKey(sAttribute))
                throw new RuntimeException(MessageFormat.format("Element does not contain attribute {0}. Element attributes: {1}.", sAttribute, attributesKeysJoined));

            var filteredAttributes = attributes.entrySet()
                    .stream()
                    .filter(x -> x.getKey().equalsIgnoreCase(sAttribute) && x.getValue().toString().equalsIgnoreCase(sAttributeValue));

            if (filteredAttributes.count() < 1)
                throw new RuntimeException(MessageFormat.format("\"Element attribute '{0}' does not contain value '{1}'. Element attribute values: {2}.", sAttribute, sAttributeValue, attributesValuesJoined));

        }, this, "");
    }

    /**
     * Method retrieves value of element attribute.
     *
     * @param sAttribute Expected element attribute.
     * @return Attribute value as string
     */
    public String getAttributeValue(String sAttribute) {
        this.exists(true);
        var attributes = getAllControlAttributes();

        return attributes.get(sAttribute).toString();
    }

    /**
     * Method retrieves all control attributes.
     *
     * @return map of attributes.
     */
    private Map<String, Object> getAllControlAttributes() {
        var executor = ((JavascriptExecutor) UIReferences.getWebDriver());
        var attributes = (Map<String, Object>) executor.executeScript
                ("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;"
                        , control());
        return attributes;
    }
}
