package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;

public class WbLabel extends WebControlBase {
    public WbLabel(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbLabel(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }


    /**
     * Verifies if the text of WebLabel is valid or not.
     *
     * @param sText   Expected text of a label.
     * @param bExists If true, checks if text exists; if false, checks if text does not exists.
     * @return Success object
     */
    public Success verifyText(String sText, Boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bExists && !control().getText().equals(sText))
                throw new RuntimeException(MessageFormat.format("Label Text '{0}' is not as expected. Expected text: {1}.", control().getText(), sText));

            if (!bExists && control().getText().equals(sText))
                throw new RuntimeException(MessageFormat.format("Text is expected not to exist. Label Text: {0}, not expected text: {1}", control().getText(), sText));

        }, this, "");
    }
}
