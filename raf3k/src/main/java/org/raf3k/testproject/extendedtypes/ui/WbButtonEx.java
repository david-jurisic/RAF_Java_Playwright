package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbButton;

public class WbButtonEx extends WbButton {
    public WbButtonEx(WebElement webElement, String alias) {
        super(webElement, alias);
    }

    public WbButtonEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbButtonEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbButtonEx(By searchBy, WebElement parent, String alias) {
        super(searchBy, parent, alias);
    }
}
