package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbDropDown;

public class WbDropDownEx extends WbDropDown {
    public WbDropDownEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbDropDownEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
