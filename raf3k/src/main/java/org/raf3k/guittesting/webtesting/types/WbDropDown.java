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

    public String firstSelectedOption = null;
    public String itemText = null;
    public String itemValue = null;

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
     * Method set value into web control and presses 'Return' key.
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
            UIReferences.actionsBuilder().moveToElement(control()).click().build().perform();
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
                        "{0} <br> Actual: {1}", sTextContains, sText));

            Suc.addArgumentsOfMethodForLog("sTextContains", sTextContains, true);

            return Suc.finish(null);
        } catch (Exception ex) {
            return Suc.finish(ex);
        }
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
    public ArrayList<String> getAllOptions() {
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

    /**
     * Method verifies if item is selected by its values.
     *
     * @param sId       String value of item to be verified.
     * @param bSelected If true, checks if item is selected, if false, check if item not selected.
     * @return Success object
     */
    public Success verifyItemSelectedByValue(String sId, boolean bSelected) {
        return UIReferences.eval().evaluate(() ->
        {
            Option selectedOption = getSelectedOption();
            if (bSelected && !selectedOption.value.equals(sId))
                throw new RuntimeException(MessageFormat.format("Element not selected.<br> Expected element: {0}<br> Selected element value: {1}, Selected element text: {2}", sId, selectedOption.value, selectedOption.text));

            if (!bSelected && selectedOption.value.equals(sId))
                throw new RuntimeException(MessageFormat.format("Element is selected.<br> Expected element not to be selected: {0}<br> Selected element value:{1}, Selected element text: {2}", sId, selectedOption.value, selectedOption.text));
        }, this, "");
    }

    /**
     * Method verifies if item exists in dropdown menu.
     *
     * @param sItem   String value of item to be verified.
     * @param bExists If true, checks if item exists, if false, check if item does not exist.
     * @return Success object
     */
    public Success verifyItemExists(String sItem, boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            this._sItemText = sItem;
            Option selectedOption = getSelectedOption();
            if (selectedOption == null && bExists)
                throw new RuntimeException(MessageFormat.format("Item does not exist in drop down.<br> Searched item {0}", sItem));

            if (selectedOption != null && !bExists)
                throw new RuntimeException(MessageFormat.format("Item exists in drop down but is not expected.<br> Searched item {0}", sItem));

        }, this, "");
    }

    /**
     * Method verifies if items exist in dropdown menu.
     *
     * @param selectedItems List of items to be verified.
     * @param bExists       If true, checks if items exists, if false, check if items does not exist.
     * @return Success object
     */
    public Success verifyItemExists(ArrayList<String> selectedItems, boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            ArrayList<String> lsExistingItems = new ArrayList<>();

            lsExistingItems.addAll(getAllOptions());

            ArrayList<String> lsItems = new ArrayList<>();
            if (bExists) {
                for (String sItem : selectedItems) {
                    if (!lsExistingItems.contains(sItem))
                        lsItems.add(sItem);
                }
            }

            if (!bExists) {
                for (String sItem : selectedItems) {
                    if (lsExistingItems.contains(sItem))
                        lsItems.add(sItem);
                }
            }

            if (lsItems.size() > 0) {
                if (bExists)
                    throw new RuntimeException("Items does exist in element, but it should not: " + String.join(", ", lsItems));

                if (!bExists)
                    throw new RuntimeException("Items does not exist in element, but it should: " + String.join(", ", lsItems));
            }

        }, this, "");
    }

    /**
     * Method which returns text of currently Selected Option in a dropdown.
     *
     * @return firstSelectedOption, Success object
     */
    public Success getFirstSelectedOptionText() {
        firstSelectedOption = null;
        Success Suc = new Success(this);
        try {
            firstSelectedOption = allOptions().stream().filter(m -> m.selected).findFirst().get().text;
            return Suc.finish(null);
        } catch (Exception ex) {
            return Suc.finish(ex);
        }
    }

    /**
     * This method select and double click on option in dropdown menu.
     *
     * @param Idx     Index of expected item.
     * @param iScroll Int of value to scroll vertically. It is set to 0 by default.
     * @return Success object
     */
    public Success selectAndDoubleClickOnOption(int Idx, int iScroll) {
        return UIReferences.eval().evaluate(() ->
        {
            this._iItemIndex = Idx;
            scrollVertical(iScroll);
            UIReferences.actionsBuilder().moveToElement(control()).doubleClick(getSelectedOption().selectedElement).build().perform();
            _ddlOpened = false;

        }, this, "");
    }

    /**
     * This method select and double click on option in dropdown menu.
     *
     * @param sItem   Name of expected item.
     * @param iScroll Int of value to scroll vertically. It is set to 0 by default.
     * @return Success object
     */
    public Success selectAndDoubleClickOnOption(String sItem, int iScroll) {
        return UIReferences.eval().evaluate(() ->
        {
            this._sItemText = sItem;
            scrollVertical(iScroll);
            UIReferences.actionsBuilder().moveToElement(control()).doubleClick(getSelectedOption().selectedElement).build().perform();
            _ddlOpened = false;

        }, this, "");
    }

    /**
     * Verifies that any item is selected, verifies that Not selected item is not selected.
     *
     * @return Success object
     */
    public Success verifyNotEmpty() {
        return UIReferences.eval().evaluate(() ->
        {
            if (allOptions() == null)
                throw new RuntimeException("Dropdown is empty");
        }, this, "");
    }

    /**
     * Method gets item text in dropdown by value.
     *
     * @param sId Gets text of item which contains sId.
     * @return itemText, Success object
     */
    public Success getItemTextByValue(String sId) {
        Success Suc = new Success(this);
        itemText = null;
        this._sItemText = null;

        try {
            this._sItemValue = sId;
            String selectedOptionText = getSelectedOption().text;
            if (selectedOptionText.equals(sId))
                itemText = selectedOptionText;

            return Suc.finish(null);
        } catch (Exception ex) {
            return Suc.finish(ex);
        }
    }

    /**
     * Method gets items 'value' attribute value in dropdown by item text.
     *
     * @param sItemText Item text for which value should be found and returned.
     * @return itemValue, Success object
     */
    public Success getItemValueByText(String sItemText) {
        Success Suc = new Success(this);
        itemValue = null;
        this._sItemValue = null;

        try {
            this._sItemText = sItemText;
            String selectedOptionText = getSelectedOption().value;
            if (selectedOptionText.equals(sItemText))
                itemValue = selectedOptionText;
            return Suc.finish(null);
        } catch (Exception ex) {
            return Suc.finish(ex);
        }
    }
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
