package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;

public class WbIcon extends WebControlBase {
    public WbIcon(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbIcon(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbIcon(By searchBy, WebElement parent, String alias) {
        super(searchBy, parent, alias);
    }
}
