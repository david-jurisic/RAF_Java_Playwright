package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.util.List;

public class WbMenu extends WebControlBase {

    public WbMenu(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbMenu(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Sets item in the web menu.
     *
     * @param sItem Name of the item to be set.
     * @return Success object
     */
    public Success setMenuItem(String sItem) {
        return UIReferences.eval().evaluate(() ->
        {
            List<WebElement> allMenuITems = control().findElements(By.tagName("li"));

            if (allMenuITems.isEmpty()) {
                allMenuITems = control().findElements(By.tagName("a"));
            }

            WebElement item = allMenuITems.stream().filter(m -> m.getText().toLowerCase().trim().equals(sItem.toLowerCase().trim())).findFirst().get();
            if (item != null) {
                UIReferences.actionsBuilder().moveToElement(item).click().build().perform();
            } else {
                throw new RuntimeException("Cannot find menu item : " + sItem);
            }

        }, this, "");
    }

    /**
     * Verifies if the menu item is present in webMenu.
     *
     * @param sItemText Text of wanted element.
     * @return Success object
     */
    public Success verifyMenuItemPresent(String sItemText) {
        return UIReferences.eval().evaluate(() ->
        {
            boolean bIsPresent = false;

            List<WebElement> allItems = control().findElements(By.tagName("li"));
            if (allItems.isEmpty()) {
                allItems = control().findElements(By.tagName("a"));
            }

            for (WebElement element : allItems) {
                String sElementText = element.getText();

                if (sElementText.equalsIgnoreCase(sItemText)) {
                    bIsPresent = true;
                    break;
                }
            }

            if (!bIsPresent) {
                throw new RuntimeException("Item is not present.");
            }

        }, this, "");
    }
}
