package org.raf3k.guittesting.webtesting.types;

import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class WbEdit extends WebControlBase {
    public WbEdit(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbEdit(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public Success setText(String sText, Boolean bSetValue, Boolean bSetWithAction, Boolean bClickControl) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bSetWithAction && bSetValue) {
                try {
                    throw new Exception("Only one parameter can be set to true,either 'bSetValue' or 'bSetWithAction'.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (bClickControl)
                control().click();

            if (!sAlias.toLowerCase().contains("password")) {
                control().clear();
            } else {
                control().sendKeys(Keys.CONTROL + "a");
                control().sendKeys(Keys.DELETE);
            }

            control().sendKeys(sText);
        }, this, "");
    }
}
