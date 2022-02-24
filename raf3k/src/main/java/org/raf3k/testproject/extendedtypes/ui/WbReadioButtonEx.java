package org.raf3k.testproject.extendedtypes.ui;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbRadioButton;

public class WbReadioButtonEx extends WbRadioButton {
    public WbReadioButtonEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbReadioButtonEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
