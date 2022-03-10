package org.raf3k.apptesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.apptesting.basetypes.AppControlBase;

public class MbIcon extends AppControlBase {

    public MbIcon(String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }

    public MbIcon(AppControlBase parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    public MbIcon(WebElement parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }
}
