package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;

public class WbComboBox extends WebControlBase {
    public WbComboBox(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbComboBox(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
