package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.util.List;

public class WbList extends WebControlBase {

    private By _SearchListItemsBy;

    public List<String> items;

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

    /**
     * Gets all controls inside web list.
     * @return List of IWebElement
     */
    public List<WebElement> getAllListItemsControls(){
        List<WebElement> allItems;
        if (_SearchListItemsBy != null)
            allItems = control().findElements(_SearchListItemsBy).stream().toList();
        else
            allItems = control().findElements(By.tagName("li")).stream().toList();
        return allItems;
    }

    /**
     * Method verifies if an item exists in list.
     * @param sItemText Item search criteria.
     * @param bExists If set to 'false' verifies if item does not exist.
     * @return Success object.
     */
    public Success verifyListItemExists(String sItemText, Boolean bExists){
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
     * @return Success object.
     */
    public Success getAllListItems(){
        return UIReferences.eval().evaluate(() ->
        {
            items = null;
            List<WebElement> allItems = getAllListItemsControls();
            for (WebElement name : allItems){
                items.add(name.getText());
            }
        }, this, "");
    }
}

