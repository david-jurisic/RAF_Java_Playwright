package org.raf3k.apptesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.apptesting.basetypes.AppControlBase;

public class MbLabel extends AppControlBase {

    public MbLabel(String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }

    public MbLabel(AppControlBase parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    public MbLabel(WebElement parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }
}
