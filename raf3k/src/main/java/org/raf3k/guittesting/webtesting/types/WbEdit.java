package org.raf3k.guittesting.webtesting.types;

import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;

public class WbEdit extends WebControlBase {
    public String getTextValue = null;

    public WbEdit() {
    }

    public WbEdit(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbEdit(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Method clears text inside the textbox.
     *
     * @param bClearWithAction Set to true to clear the text with action.It is false by default.
     * @return Success object.
     */
    public Success clear(boolean bClearWithAction) {
        return UIReferences.eval().evaluate(() ->
        {
            if (!bClearWithAction)
                control().clear();
            else {
                control().sendKeys(Keys.CONTROL + "a");
                control().sendKeys(Keys.DELETE);
            }

            String controlText = getControlText();
            if (controlText != null && !controlText.isEmpty())
                throw new RuntimeException("Control " + sAlias + " text not cleared.");

        }, this, "");
    }

    /**
     * Method sets text inside the textbox.
     *
     * @param sText          Text to be set.
     * @param bSetValue      If set to 'true' text will be set with action, it is 'false' by default.
     * @param bSetWithAction If set to 'true' text will be set with action.It is 'false' by default.
     * @param bClickControl  If set to 'true' web control will be clicked on, it is 'false' by default.
     * @return Success object
     */
    public Success setText(String sText, Boolean bSetValue, Boolean bSetWithAction, Boolean bClickControl) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bSetWithAction && bSetValue)
                throw new RuntimeException("Only one parameter can be set to true,either 'bSetValue' or 'bSetWithAction'.");

            if (bClickControl)
                control().click();

            if (!sAlias.toLowerCase().contains("password")) {
                control().clear();
            } else {
                control().sendKeys(Keys.CONTROL + "a");
                control().sendKeys(Keys.DELETE);
            }

            if (bSetValue)
                ((JavascriptExecutor) UIReferences.getWebDriver()).executeScript("arguments[0].value='" + sText + "'", control());
            else if (bSetWithAction)
                UIReferences.actionsBuilder().sendKeys(control(), sText).build().perform();
            else
                control().sendKeys(sText);

        }, this, "");
    }

    /**
     * Method sets text inside the textbox.
     *
     * @param sText Text to be set.
     * @return Success object
     */
    public Success setText(String sText) {
        return UIReferences.eval().evaluate(() ->
        {
            if (!sAlias.toLowerCase().contains("password")) {
                control().clear();
            } else {
                control().sendKeys(Keys.CONTROL + "a");
                control().sendKeys(Keys.DELETE);
            }

            control().sendKeys(sText);

        }, this, "");
    }

    /**
     * Method verifies text in textbox.
     *
     * @param sText Text to be verified inside the textbox.
     * @return Success object.
     */
    public Success verifyText(String sText) {
        return UIReferences.eval().evaluate(() ->
        {
            String sControlvalue = control().getAttribute("value");

            if (!sControlvalue.equals(sText))
                throw new RuntimeException("Text not verified. Text box text: " + sControlvalue + " , searched text " + sText + ".");

        }, this, "");
    }

    /**
     * Method verifies part of the text in textbox.
     *
     * @param sText Text to be verified inside the textbox.
     * @return Success object.
     */
    public Success verifyTextContains(String sText) {
        return UIReferences.eval().evaluate(() ->
        {
            String sControlvalue = control().getAttribute("value");

            if (!sControlvalue.contains(sText))
                throw new RuntimeException("Text not verified. Text box text: " + control().getText() + " , searched text " + sText + ".");

        }, this, "");
    }

    /**
     * Method verifies if textbox is empty or not empty.
     *
     * @param bEmpty Set to 'false' if you want to check if textbox is not empty. It is 'true' by default.
     * @return Success object.
     */
    public Success verifyEmpty(boolean bEmpty) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            String sControlvalue = control().getAttribute("value").trim();

            if (bEmpty && sControlvalue != null && !sControlvalue.isEmpty())
                throw new RuntimeException("Control " + sAlias + " text is not empty.");

            if (!bEmpty && sControlvalue == null && sControlvalue.isEmpty())
                throw new RuntimeException("Control " + sAlias + " text is empty.");

        }, this, "");
    }

    /**
     * Method gets control text
     *
     * @return Success object, getTextValue Out parameter controls text
     */
    public Success getText() {
        getTextValue = "";
        Success Suc = new Success(this);
        try {
            if (control().getText() != null && !control().getText().isEmpty())
                getTextValue = control().getText();
            else
                getTextValue = control().getAttribute("value");

            Suc.addArgumentsOfMethodForLog("getTextValue", getTextValue, true);
            return Suc.finish(null);
        } catch (Exception ex) {
            return Suc.finish(ex);
        }
    }
}
