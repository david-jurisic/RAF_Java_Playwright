package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbWrapper;

public class WbWrapperEx extends WbWrapper {
    public WbWrapperEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbWrapperEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
