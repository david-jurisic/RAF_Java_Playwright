package org.raf3k.guittesting.webtesting.basetypes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.raf3k.shared.DebugLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.shared.ControlObject;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.Helpers;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
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
    public long iSeconds = -1;

    public WebElement control() {
        try {
            waitForLoaders();
            if (checkIfControlStale())
                _Controlreference = UIReferences.getWebEng().findControl(sControlType, searchBy, parent, bMustBeVisible, iTimeoutOverride);
            return _Controlreference;
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }

    public WebControlBase() {
        this.sControlType = this.getClass().getSimpleName();
        this.sPath = getFullsPath();
    }

    public WebControlBase(By searchBy, String alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias = alias;
        this.sPath = getFullsPath();
    }

    public WebControlBase(By searchBy, WebControlBase parent, String alias) {
        this.sControlType = this.getClass().getName();
        this.searchBy = searchBy;
        this.sAlias = alias;
        this.sPath = getFullsPath();
        this.parent = parent;
    }

    public WebControlBase(WebElement control, String alias) {
        this.sControlType = this.getClass().getName();
        this.sAlias = alias;
        this.sPath = getFullsPath();

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

    /**
     * Method retrieves parent web control.
     *
     * @return Parent web control of current web control
     */
    public WebElement getParent() {
        return control().findElement(By.xpath("parent::*"));
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
        UIReferences.getWebEng().waitForLoaders();
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
            return control().getAttribute("value");
        else
            return control().getText();
    }

    /**
     * Method clicks on web control.
     *
     * @return Success object.
     */
    public Success click() {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            this.control().click();
        }, this, "");
    }

    /**
     * Moves to element and clicks.
     *
     * @return Success object.
     */
    public Success moveToElementAndClick() {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            Actions actions = new Actions(UIReferences.getWebDriver());
            actions.moveToElement(control()).click().build().perform();
        }, this, "");
    }

    /**
     * Method moves the mouse to the element.
     *
     * @return Success object.
     */
    public Success scrollIntoView() {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            UIReferences.actionsBuilder().moveToElement(control()).build().perform();
        }, this, "");
    }

    /**
     * Method verifies the web control is displayed.
     *
     * @param bDisplayed Bool parameter, if set to false, verifies the web control is not displayed.
     * @return Success object.
     */
    public Success displayed(Boolean bDisplayed) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bDisplayed && !this.isDisplayed())
                throw new RuntimeException(MessageFormat.format("Web control {0} is not displayed.", sAlias));
            if (!bDisplayed && this.isDisplayed())
                throw new RuntimeException(MessageFormat.format("Web control {0} is displayed.", sAlias));
        }, this, "");
    }

    /**
     * Method verifies the web control is displayed.
     *
     * @param waitTime   Time to wait for text to be displayed.
     * @param bDisplayed Bool parameter, if set to false, verifies the web control is not displayed.
     * @return Success object.
     */
    public Success displayed(int waitTime, boolean bDisplayed) {
        return UIReferences.eval().evaluate(() ->
        {
            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), Duration.ofSeconds(waitTime));

            if (bDisplayed && wait.until(ExpectedConditions.visibilityOfElementLocated(searchBy)) == null)
                throw new RuntimeException(MessageFormat.format("Web control {0} is not displayed in {1} seconds.", sAlias, waitTime));
            if (!bDisplayed && wait.until(ExpectedConditions.visibilityOfElementLocated(searchBy)) != null)
                throw new RuntimeException(MessageFormat.format("Web control {0} is displayed in {1} seconds.", sAlias, waitTime));
        }, this, "");
    }

    /**
     * Method retrieves web control display value.
     *
     * @return Web control displayed bool value.
     */
    public Boolean isDisplayed() {
        if (control() == null)
            return false;

        return control().isDisplayed();
    }

    /**
     * Method verifies size of the web control.
     *
     * @param iWidth  Expected width of web control.
     * @param iHeight Expected height of web control.
     * @return Success object.
     */
    public Success verifySize(Integer iWidth, Integer iHeight) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            var size = control().getSize();
            if (size.height != iHeight)
                throw new RuntimeException(MessageFormat.format("Height not verified. Height is {0} but expected height is {1}"
                        , size.height, iHeight));
            if (size.width != iWidth)
                throw new RuntimeException(MessageFormat.format("Width not verified. Width is {0} but expected Width is {1}"
                        , size.width, iWidth));
        }, this, "");
    }

    /**
     * Method verifies size of the web control.
     *
     * @param iXAxis Expected width of web control.
     * @param iYAxis Expected height of web control.
     * @return Success object.
     */
    public Success verifyPosition(Integer iXAxis, Integer iYAxis) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            var location = control().getLocation();
            if (location.getX() != iXAxis)
                throw new RuntimeException(MessageFormat.format("Location not verified. X axis is {0} but expected value is {1}"
                        , location.getX(), iXAxis));
            if (location.getY() != iYAxis)
                throw new RuntimeException(MessageFormat.format("Location not verified. Y axis is {0} but expected value is {1}"
                        , location.getY(), iYAxis));
        }, this, "");
    }

    /**
     * Method verifies web control is enabled.
     *
     * @param bEnabled Set to 'false' if you want to check if web control is not enabled. Set to true if you want to check if web control is enabled.
     * @return Success object.
     */
    public Success enabled(Boolean bEnabled) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            if (!bEnabled && control().isEnabled()) {
                throw new RuntimeException("Control is enabled.");
            }

            if (bEnabled && !control().isEnabled()) {
                throw new RuntimeException("Control is not enabled");
            }

        }, this, "");
    }

    /**
     * Method retrieves web control enabled value.
     *
     * @return Web control enabled value.
     */
    public boolean isEnabled() {
        if (control().isEnabled() && control().isEnabled())
            return true;
        else
            return false;
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
     * Method throws exception if web control doesn't exist.
     */
    public void exists() {
        if (control() == null)
            throw new RuntimeException(MessageFormat.format("Web control {0} does not exists.", sAlias));
    }

    /**
     * Method clicks on web control and holds mouse button down for one second before releasing.
     *
     * @return Success object.
     */
    public Success clickAndHold() {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            actionsBuilder.clickAndHold(control()).build().perform();
            Helpers.waitForAction(1);
            actionsBuilder.release().build().perform();

        }, this, "");
    }

    /**
     * Method return click and hold metrics.
     *
     * @return Success object, iSeconds Out parameter
     */
    public Success clickAndHoldReturnMetric() {
        iSeconds = 0;
        Success Success = new Success(this);
        try {
            this.exists();
            LocalDateTime startTime = LocalDateTime.now();
            actionsBuilder.clickAndHold(control()).build().perform();
            Helpers.waitForAction(1);
            actionsBuilder.release().build().perform();
            LocalDateTime finishTime = LocalDateTime.now();
            Duration time = Duration.between(startTime, finishTime);
            iSeconds = time.toSeconds();

            return Success.finish(null);
        } catch (Exception ex) {
            iSeconds = -1;
            return Success.finish(ex);
        }

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
            this.exists();

            Map<String, Object> attributes = getAllControlAttributes();
            String attributesKeysJoined = attributes.keySet().stream()
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
            this.exists();

            Map<String, Object> attributes = getAllControlAttributes();
            String attributesKeysJoined = attributes.keySet().stream()
                    .map(o -> o + ", ").collect(Collectors.joining());
            String attributesValuesJoined = attributes.values().stream()
                    .map(o -> o.toString() + ", ").collect(Collectors.joining());

            if (!attributes.containsKey(sAttribute))
                throw new RuntimeException(MessageFormat.format("Element does not contain attribute {0}. Element attributes: {1}.", sAttribute, attributesKeysJoined));

            var filteredAttributes = attributes.entrySet()
                    .stream()
                    .filter(x -> x.getKey().equalsIgnoreCase(sAttribute) && x.getValue().toString().equalsIgnoreCase(sAttributeValue));

            if (filteredAttributes.count() < 1)
                throw new RuntimeException(MessageFormat.format("Element attribute '{0}' does not contain value '{1}'. Element attribute values: {2}.", sAttribute, sAttributeValue, attributesValuesJoined));

        }, this, "");
    }

    /**
     * Method verifies if element attribute contains value.
     *
     * @param sAttribute      Expected element attribute.
     * @param sAttributeValue Expected element attribute value.
     * @return Success object.
     */
    public Success verifyAttributeValueContains(String sAttribute, String sAttributeValue) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            Map<String, Object> attributes = getAllControlAttributes();
            String attributesKeysJoined = attributes.keySet().stream()
                    .map(o -> o + ", ").collect(Collectors.joining());
            String attributesValuesJoined = attributes.values().stream()
                    .map(o -> o.toString() + ", ").collect(Collectors.joining());

            if (!attributes.containsKey(sAttribute))
                throw new RuntimeException(MessageFormat.format("Element does not contain attribute {0}. Element attributes: {1}.", sAttribute, attributesKeysJoined));

            var filteredAttributes = attributes.entrySet()
                    .stream()
                    .filter(x -> x.getKey().equalsIgnoreCase(sAttribute) && x.getValue().toString().toLowerCase().contains(sAttributeValue));

            if (filteredAttributes.count() < 1)
                throw new RuntimeException(MessageFormat.format("Element attribute '{0}' does not contain value '{1}'. Element attribute values: {2}.", sAttribute, sAttributeValue, attributesValuesJoined));

        }, this, "");
    }

    /**
     * Method retrieves value of element attribute.
     *
     * @param sAttribute Expected element attribute.
     * @return Attribute value as string
     */
    public String getAttributeValue(String sAttribute) {
        this.exists();

        Map<String, Object> attributes = getAllControlAttributes();

        return attributes.get(sAttribute).toString();
    }

    /**
     * Method retrieves all control attributes.
     *
     * @return map of attributes.
     */
    private Map<String, Object> getAllControlAttributes() {
        JavascriptExecutor executor = ((JavascriptExecutor) UIReferences.getWebDriver());
        Map<String, Object> attributes = (Map<String, Object>) executor.executeScript
                ("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;"
                        , control());
        return attributes;
    }

    private String getFullsPath() {
        String rootClass = Thread.currentThread().getStackTrace()[6].getClassName();
        String mapFolders = UIReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[5].getClassName());

        if (rootClass.contains(".")) {
            String[] arrayFolders = rootClass.split("\\.");
            String rootFolder = "";
            for (int i = 0; i < arrayFolders.length - 1; i++) {
                rootFolder += arrayFolders[i] + ".";
            }

            return rootFolder + mapFolders;
        } else {
            return mapFolders;
        }
    }
}
