package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbEdit;

public class WbEditEx extends WbEdit {
    public WbEditEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbEditEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
