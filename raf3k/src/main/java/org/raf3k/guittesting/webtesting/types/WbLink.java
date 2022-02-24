package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;

public class WbLink extends WebControlBase {
    public WbLink(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbLink(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbLink(By searchBy, WebElement parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Verifies if the text of WebLink is valid or not.
     *
     * @param sText   Expected text of a link.
     * @param bExists If true, checks if text exists,if false, checks if text does not exists.
     * @return Success object
     */
    public Success verifyText(String sText, boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bExists && !control().getText().equals(sText))
                throw new RuntimeException(MessageFormat.format("Link Text '{0}' is not as expected. Expected text: {1}.", control().getText(), sText));

            if (!bExists && control().getText().equals(sText))
                throw new RuntimeException(MessageFormat.format("Text is expected not to exist. Link Text: {0}, not expected text: {1}", control().getText(), sText));

        }, this, "");
    }

    /**
     * Verifies if the text of WebLink contains given text or not.
     *
     * @param sText   Expected string that link text contains.
     * @param bExists If true, checks if text exists,if false, checks if text does not exists.
     * @return Success object
     */
    public Success verifyTextContains(String sText, boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bExists && !control().getText().contains(sText))
                throw new RuntimeException(MessageFormat.format("Link Text '{0}' is not as expected. Expected text: {1}.", control().getText(), sText));

            if (!bExists && control().getText().contains(sText))
                throw new RuntimeException(MessageFormat.format("Text is expected not to exist. Link Text: {0}, not expected text: {1}", control().getText(), sText));

        }, this, "");
    }
}
