package org.raf3k.apptesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.apptesting.AppReferences;
import org.raf3k.apptesting.basetypes.AppControlBase;
import org.raf3k.shared.logging.Success;

public class MbButton extends AppControlBase {

    public MbButton(String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }

    public MbButton(AppControlBase parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }

    public MbButton(WebElement parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }
}
