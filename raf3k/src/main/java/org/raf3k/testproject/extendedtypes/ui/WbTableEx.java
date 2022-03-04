package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbTable;

public class WbTableEx extends WbTable {
    public WbTableEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbTableEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
