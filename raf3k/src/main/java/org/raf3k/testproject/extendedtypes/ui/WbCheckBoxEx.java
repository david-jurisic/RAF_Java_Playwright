package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbCheckBox;

public class WbCheckBoxEx extends WbCheckBox {
    public WbCheckBoxEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbCheckBoxEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}