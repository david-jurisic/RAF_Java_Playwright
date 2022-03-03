package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.util.ArrayList;
import java.util.List;

public class WbList extends WebControlBase {

    private By _SearchListItemsBy;

    public List<String> items = new ArrayList<String>();

    public WbList(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbList(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbList(By searchBy, By searchListItemsBy, String alias) {
        super(searchBy, alias);
        _SearchListItemsBy = searchListItemsBy;
    }

    public WbList(WebElement webElement, String alias) {
        super(webElement, alias);
    }

    /**
     * Gets all controls inside web list.
     *
     * @return List of IWebElement
     */
    public List<WebElement> getAllListItemsControls() {
        List<WebElement> allItems;
        if (_SearchListItemsBy != null)
            allItems = control().findElements(_SearchListItemsBy).stream().toList();
        else
            allItems = control().findElements(By.tagName("li")).stream().toList();
        return allItems;
    }

    /**
     * Method verifies if an item exists in list.
     *
     * @param sItemText Item search criteria.
     * @param bExists   If set to 'false' verifies if item does not exist.
     * @return Success object.
     */
    public Success verifyListItemExists(String sItemText, Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            List<WebElement> allItems = getAllListItemsControls();

            WebElement option = allItems.stream().filter(x -> x.getText().equals(sItemText)).findFirst().orElse(null);

            if (option == null && bExists)
                throw new RuntimeException(String.format("Item does not exist in list.<br> Searched item {0}", sItemText));

            if (option != null && !bExists)
                throw new RuntimeException(String.format("Item exists in list but is not expected.<br> Searched item {0}", sItemText));
        }, this, "");
    }

    /**
     * Method gets all list items.
     *
     * @return List of names of all the list items.
     */
    public List<String> getAllListItems() {
        items.clear();
        List<WebElement> allItems = getAllListItemsControls();
        for (WebElement name : allItems) {
            name.getText();
            items.add(name.getText());
        }
        return items;
    }

    /**
     * Method gets count of all items in list.
     *
     * @return count of all list items.
     */
    public int getListItemsCount() {
        List<WebElement> allItems = getAllListItemsControls();
        return (int) allItems.stream().count();
    }

    /**
     * Method finds an item by text.
     *
     * @param sItemText item search criteria.
     * @return WebList object.
     */
    public WbList getListItemByText(String sItemText) {
        List<WebElement> allItems = getAllListItemsControls();

        WebElement option = allItems.stream().filter(x -> x.getText().equals(sItemText)).findFirst().orElse(null);

        int iIndex = allItems.indexOf(option);
        return new WbList(option, this.sPath + "DynamicWebList[" + iIndex + "]");
    }

    /**
     * Method finds an item by index.
     *
     * @param iItemIndex item index.
     * @return WebList object.
     */
    public WbList getListItemByIndex(int iItemIndex) {
        List<WebElement> allItems = getAllListItemsControls();

        if (iItemIndex >= allItems.stream().count())
            throw new RuntimeException(String.format("Index {0} out of range. There is only {1} available.", iItemIndex, allItems.stream().count()));

        WebElement webSearchedElement = allItems.get(iItemIndex);

        return new WbList(webSearchedElement, this.sPath + "DynamicWebList[" + iItemIndex + "]");
    }

    /**
     * Method verifies if the list is empty or not.
     *
     * @param bEmpty Set to 'false' if you want to check if the list is not empty. Set to 'true' of you want to check if the list is empty.
     * @return Success object.
     */
    public Success verifyEmpty(Boolean bEmpty) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bEmpty && getListItemsCount() > 0)
                throw new RuntimeException(String.format("List is not empty but expected to be empty. Number of entries counted: {0}", getListItemsCount()));

            if (!bEmpty && getListItemsCount() == 0)
                throw new RuntimeException(String.format("List is empty but it is not expected not to be."));
        }, this, "");
    }

    /**
     * Method verifies if the list is empty or not.
     *
     * @param bEmpty Set to 'false' if you want to check if the list is not empty. Set to 'true' of you want to check if the list is empty.
     * @return Boolean.
     */
    public Boolean verifyEmpty(){
        if(getListItemsCount() > 0)
            return false;
        else
            return true;
    }
}

