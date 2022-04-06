package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;

public class WbButton extends WebControlBase {
    public WbButton() {
    }

    public WbButton(WebElement webElement, String alias) {
        super(webElement, alias);
    }

    public WbButton(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbButton(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbButton(By searchBy, WebElement parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Method verifies button text.
     *
     * @param sText   Control text that needs to be verified.
     * @param bExists If set to 'true', checks if text exists. If set to 'false', checks if texts does not exist.
     * @return Success object.
     */
    public Success verifyText(String sText, boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            if (bExists && !control().getText().equals(sText))
                throw new RuntimeException(MessageFormat.format("Cannot verify text. Expected text: {0}. Control text: {1}", sText, control().getText()));
            if (!bExists && control().getText().equals(sText))
                throw new RuntimeException(MessageFormat.format("Text exists. Expected text: {0}. Control text: {1}", sText, control().getText()));

        }, this, "");
    }
}
