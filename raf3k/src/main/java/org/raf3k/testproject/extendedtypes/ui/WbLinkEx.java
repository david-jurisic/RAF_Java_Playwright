package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbLink;

public class WbLinkEx extends WbLink {
    public WbLinkEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbLinkEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbLinkEx(By searchBy, WebElement parent, String alias) {
        super(searchBy, parent, alias);
    }
}
