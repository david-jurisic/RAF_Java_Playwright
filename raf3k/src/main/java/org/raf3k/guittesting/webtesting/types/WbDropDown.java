package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.enumerations.Operations;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WbDropDown extends WebControlBase {

    public WbDropDown(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbDropDown(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    private String _sItemText;
    private boolean _ddlOpened;
    private int _iItemIndex = -1;
    private String _sItemContains;
    private String _sItemValue;

    private ArrayList<Option> allOptions() {
        try {
            ArrayList<Option> options = new ArrayList<>();

            switch (control().getTagName()) {
                case "input":
                case "md-select":
                    if (!_ddlOpened) {
                        control().click();
                        Thread.sleep(2000);
                        _ddlOpened = true;
                    }

                    for (WebElement element : UIReferences.getWebDriver().findElements(By.tagName("md-option"))) {
                        Option option = new Option(
                                element.getTagName(),
                                element.getAttribute("class").contains("selected"),
                                element.getText(),
                                Optional.ofNullable(element.getAttribute("value")).orElse(""),
                                element,
                                control());

                        options.add(option);
                    }
                    break;
                case "ul":
                    actionsBuilder.moveToElement(control()).click().build().perform();

                    for (WebElement element : UIReferences.getWebDriver().findElements(By.tagName("li"))) {
                        Option option = new Option(
                                element.getTagName(),
                                element.getAttribute("selected") != null,
                                element.getText(),
                                Optional.ofNullable(element.getAttribute("value")).orElse(""),
                                element,
                                control());

                        options.add(option);
                    }
                    break;
                case "div":
                    actionsBuilder.moveToElement(control()).click().build().perform();

                    List<WebElement> elements = control().findElements(By.tagName("li"));

                    if (elements.isEmpty())
                        elements = UIReferences.getWebDriver().findElements(By.className("select2-results__option"));

                    for (WebElement element : UIReferences.getWebDriver().findElements(By.tagName("li"))) {
                        Option option = new Option(
                                element.getTagName(),
                                element.getAttribute("selected") != null,
                                element.getText(),
                                Optional.ofNullable(element.getAttribute("value")).orElse(""),
                                element,
                                control());

                        options.add(option);
                    }
                    break;
                case "select":
                default:
                    Select selectElement = new Select(control());

                    for (WebElement element : selectElement.getOptions()) {
                        Option option = new Option(
                                element.getTagName(),
                                element.getAttribute("selected") != null,
                                element.getText(),
                                Optional.ofNullable(element.getAttribute("value")).orElse(""),
                                element,
                                control());

                        options.add(option);
                    }
                    break;
            }

            return options;
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }

    private Option getSelectedOption() {
        try {
            if (_sItemText != null && !_sItemText.isEmpty())
                return allOptions().stream().filter(m -> m.text.equals(_sItemText)).findFirst().orElse(null);
            else if (_iItemIndex != -1) {
                if (_iItemIndex > allOptions().size())
                    throw new RuntimeException(MessageFormat.format("Expected index '{0}' is bigger than actual option count '{1}'.", _iItemIndex, allOptions().size()));
                return allOptions().get(_iItemIndex - 1);
            } else if (_sItemContains != null && !_sItemContains.isEmpty())
                return allOptions().stream().filter(m -> m.text.contains(_sItemContains) || m.value.contains(_sItemContains)).findFirst().get();
            else if (_sItemValue != null && !_sItemValue.isEmpty()) {
                return allOptions().stream().filter(m -> m.value.equals(_sItemValue)).findFirst().orElse(null);
            } else
                return null;
        } catch (Exception ex) {
            DebugLog.add(ex);
            return null;
        }
    }

    private void scrollVertical(int value) {
        ((JavascriptExecutor) UIReferences.getWebDriver()).executeScript(MessageFormat.format("scroll(0,{0});", value));
    }

    private Success SendEnter(String sExpectedTagName, String sActualTagname) {
        return UIReferences.eval().evaluate(() ->
        {
            if (sActualTagname.equals(sExpectedTagName)) {
                Actions closeDDL = new Actions(UIReferences.getWebDriver());
                closeDDL.sendKeys(Keys.ENTER).perform();
                _ddlOpened = false;
            }
        }, this, "");
    }

    /**
     * Method selects item inside dropdown menu.
     *
     * @param sItem   Item to be selected.
     * @param iScroll Int of value to scroll vertically. It is set to 0 by default.
     * @return Success object
     */
    public Success setItem(String sItem, int iScroll) {

        return UIReferences.eval().evaluate(() ->
        {
            this._sItemText = sItem;
            scrollVertical(iScroll);
            getSelectedOption().selectedElement.click();
            _ddlOpened = false;

        }, this, "");
    }

    /**
     * Method verifies if item is selected in dropdown menu.
     *
     * @param sItem     Item to be verified.
     * @param bSelected If true, checks if item is selected, if false, check if item not selected.
     * @return Success object
     */
    public Success verifySelected(String sItem, boolean bSelected) {

        return UIReferences.eval().evaluate(() ->
        {
            this._sItemText = sItem;

            boolean sSelectedItemValue = getSelectedOption().selected;

            if (bSelected && !sSelectedItemValue)
                throw new RuntimeException(MessageFormat.format("Item '{0}' is not selected. Selected item is: '{1}'", sItem, sSelectedItemValue));

            if (!bSelected && sSelectedItemValue)
                throw new RuntimeException(MessageFormat.format("Item '{0}' is selected.", sSelectedItemValue));

            SendEnter("md-option", getSelectedOption().tagName);

        }, this, "");
    }

    /**
     * Verifies option count.
     *
     * @param iExpectedOptionCount Expected option count.
     * @param operation            Verifies that dropdown has equal/less/more than options specified.
     * @return Success object
     */
    public Success verifyOptionCount(int iExpectedOptionCount, Operations operation) {
        return UIReferences.eval().evaluate(() ->
        {
            int iCnt = allOptions().size();

            switch (operation) {
                case Equals:
                    if (iCnt != iExpectedOptionCount)
                        throw new RuntimeException(MessageFormat.format("Option count not as expected. <br>Expected: {0} <br> Current: {1}",
                                iExpectedOptionCount, iCnt));
                    break;
                case LessThan:
                    if (iCnt >= iExpectedOptionCount)
                        throw new RuntimeException(MessageFormat.format("Option count not as expected.<br>Expected to be less than: {0} <br> Current: {1}",
                                iExpectedOptionCount, iCnt));
                    break;
                case MoreThan:
                    if (iCnt <= iExpectedOptionCount)
                        throw new RuntimeException(MessageFormat.format("Option count not as expected.<br>Expected to be more than: {0} <br> Current: {1}",
                                iExpectedOptionCount, iCnt));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }

            SendEnter("md-option", getSelectedOption().tagName);

        }, this, "");
    }

    /**
     * Methods types value into web control and presses 'Return' key.
     *
     * @param sText Text to be set in web control.
     * @return boolean
     */
    public boolean setControlTextAndPressEnter(String sText) {
        control().sendKeys(sText);
        control().sendKeys(Keys.RETURN);
        return true;
    }

    /**
     * Methods types value into web control.
     *
     * @param sText Text to be set in web control.
     * @return boolean
     */
    public boolean setControlText(String sText) {
        control().sendKeys(sText);
        return true;
    }

    /**
     * Method clicks on button with action builder.
     *
     * @return Success object
     */
    public Success clickWithAction() {

        return UIReferences.eval().evaluate(() ->
        {
            actionsBuilder.moveToElement(control()).click().build().perform();
            _ddlOpened = true;

        }, this, "");
    }

    /**
     * Method sets item in drop down.
     *
     * @param iItemIndex Index of item that needs to be set.
     * @param iScroll    Int of value to scroll vertically. It is set to 0 by default.
     * @return Success object
     */
    public Success setItem(int iItemIndex, int iScroll) {
        return UIReferences.eval().evaluate(() ->
        {
            this._iItemIndex = iItemIndex;
            scrollVertical(iScroll);
            getSelectedOption().selectedElement.click();
            _ddlOpened = false;

        }, this, "");
    }

    /**
     * Method verifies if selected option contains expected value.
     *
     * @param sTextContains Value that selected item contains.
     * @return Success object
     */
    public Success verifyFirstSelectedOptionTextContains(String sTextContains) {
        String sText = null;
        Success Suc = new Success(this);
        try {
            sText = allOptions().stream().filter(m -> m.selected).findFirst().get().text;
            if (!sText.contains(sTextContains))
                throw new RuntimeException(MessageFormat.format("Selected option text does not contain expected substring. <br> Expected substring: " +
                        "'{0}' <br> Actual: '{1}'", sTextContains, sText));

            Suc.addArgumentsOfMethodForLog("sTextContains", sTextContains, true);

            return Suc.finish(null);
        } catch (Exception ex) {
            return Suc.finish(ex);
        }
    }

    /**
     * Method sets item in drop down by ID, which is value of selectable item.
     *
     * @param sId     Value of item that needs to be set.
     * @param iScroll Int of value to scroll vertically. It is set to 0 by default.
     * @return Success object
     */
    public Success setItemByValue(String sId, int iScroll) {

        return UIReferences.eval().evaluate(() ->
        {
            this._sItemValue = sId;
            scrollVertical(iScroll);
            getSelectedOption().selectedElement.click();

        }, this, "");
    }

    /**
     * Method selects items inside dropdown menu.
     *
     * @param sItems Items to be selected.
     * @return Success object
     */
    public Success setMultipleItem(List<String> sItems) {
        return UIReferences.eval().evaluate(() ->
        {
            for (String item : sItems) {
                this._sItemText = item;
                getSelectedOption().selectedElement.click();
            }

        }, this, "");
    }

    /**
     * Method retrieves all options in dropdown menu.
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> GetAllOptions() {
        try {
            ArrayList<String> sAllOptions = new ArrayList<>();

            for (Option option : allOptions()) {
                sAllOptions.add(option.text);
            }

            return sAllOptions;
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    /**
     * Method verifies if item is selected in dropdown menu.
     *
     * @param sItem     Item to be verified.
     * @param sClass    Class of an item to be verified.
     * @param bSelected If true, checks if item is selected, if false, check if item not selected.
     * @return Success object
     */
    public Success verifySelected(String sItem, String sClass, boolean bSelected) {

        return UIReferences.eval().evaluate(() ->
        {
            WebElement selectList = control().findElement(By.className(sClass));
            String sSelectedItem = selectList.getText();

            if (bSelected && !sSelectedItem.equalsIgnoreCase(sItem))
                throw new RuntimeException(MessageFormat.format("Item '{0}' is not selected. Selected item is: '{1}'", sItem, control().getText()));

            if (!bSelected && sSelectedItem.equalsIgnoreCase(sItem))
                throw new RuntimeException(MessageFormat.format("Item '{0}' is selected.", control().getText()));

        }, this, "");
    }

//    public Success setItem(int iItemIndex, out string sSelectedItem, int iScroll = 0)
//    {


//    }

}

class Option {
    public String tagName;
    public boolean selected;
    public String text;
    public String value;
    public WebElement selectedElement;
    public WebElement parentElement;

    public Option(String _TagName, boolean _Selected, String _Text, String _Value, WebElement _SelectedElement, WebElement _ParentElement) {
        this.tagName = _TagName;
        this.selected = _Selected;
        this.text = _Text;
        this.value = _Value;
        this.selectedElement = _SelectedElement;
        this.parentElement = _ParentElement;
    }
}
