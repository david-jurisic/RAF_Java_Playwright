package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbList;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;
import java.util.List;

public class WbListEx extends WbList {
    private By searchListItemsBy;

    public WbListEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbListEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbListEx(By searchBy, By searchListItemsBy, String alias) {
        super(searchBy, alias);
        this.searchListItemsBy = searchListItemsBy;
    }

    public WbListEx(WebElement webElement, String alias) {
        super(webElement, alias);
    }

    public WbListEx() {
    }

    public Success selectAddress(String searchAddress) {
        Success success = new Success(this);
        try {
            exists();
            List<WebElement> allAddresses = control().findElements(By.className("ng-binding"));
            for (WebElement address : allAddresses) {
                if (address.getText().toLowerCase().contains(searchAddress.toLowerCase())) {
                    address.click();
                    break;
                }
            }
            return success.finish((Exception) null);
        } catch (Exception ex) {
            return success.finish(ex);
        }
    }

    public Success selectItemByText(String sItem) {
        Success success = new Success(this);
        try {
            exists();
            List<WebElement> allItems = control().findElements(By.cssSelector("[class='r6customerheader-menu-title ng-binding']"));
            for (WebElement item : allItems) {
                if (item.getText().toLowerCase().contains(sItem.toLowerCase())) {
                    item.click();
                    break;
                }
            }
            return success.finish((Exception) null);
        } catch (Exception ex) {
            return success.finish(ex);
        }
    }

    public Success verifyShoppingCartItemExist(String sItem, boolean bExists) {
        return UIReferences.eval().evaluate(() -> {
            this.exists();
            List<WebElement> allItems = control().findElements(By.xpath("//div[@class='r6shoppingcard-content']/div"));
            boolean itemFound = false;

            for (WebElement item : allItems) {
                if (item.getText().equalsIgnoreCase(sItem) && bExists) {
                    itemFound = true;
                    break;
                } else if (!item.getText().equalsIgnoreCase(sItem) && !bExists) {
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                if (bExists) {
                    throw new RuntimeException(MessageFormat.format("Item with that text does not exist in list.<br> Searched item: \"{0}\"", sItem));
                } else {
                    throw new RuntimeException(MessageFormat.format("Item with that text exists in list but is not expected.<br> Searched item: \"{0}\"", sItem));
                }
            }
        }, this, "");

    }

    public WbListEx findFeatureFilterByText(String sItem) {
        Success success = new Success(this);
        try {
            exists();
            List<WebElement> allItems = control().findElements(By.xpath("//div[@class='r6offerfeature-category ng-scope']"));
            for (WebElement item : allItems) {
                if (item.getText().contains(sItem)) {
                    return new WbListEx(item, this.sPath);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public WbListEx findFeatureGroupByText(String sItem) {
        Success success = new Success(this);
        try {
            exists();
            List<WebElement> allItems = control().findElements(By.xpath("//div[@class='r6offerfeature-refpoint ng-scope']"));
            for (WebElement item : allItems) {
                if (item.getText().contains(sItem)) {
                    return new WbListEx(item.findElement(By.cssSelector("div.r6offerfeature-add button")), this.sPath);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}