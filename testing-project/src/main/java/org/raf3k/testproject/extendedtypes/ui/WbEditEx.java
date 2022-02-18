package org.raf3k.testproject.extendedtypes.ui;

import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbEdit;
import org.openqa.selenium.By;

public class WbEditEx extends WbEdit {
    public WbEditEx(By SearchBy, String Alias) {
        super(SearchBy, Alias);
    }

    public WbEditEx(By SearchBy, WebControlBase Parent, String Alias) {
        super(SearchBy, Parent, Alias);
    }
}
