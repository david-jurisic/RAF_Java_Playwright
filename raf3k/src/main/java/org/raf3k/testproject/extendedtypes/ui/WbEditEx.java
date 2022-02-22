package org.raf3k.testproject.extendedtypes.ui;

import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbEdit;
import org.openqa.selenium.By;

public class WbEditEx extends WbEdit {
    public WbEditEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbEditEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
