package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

public class WbCheckBox extends WebControlBase {
    public boolean getCheckedValue = false;

    public WbCheckBox() {
    }

    public WbCheckBox(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbCheckBox(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Verifies if the checkbox is selected or not.
     *
     * @param bChecked Set to 'false' if you want to check if checkbox is not selected. It is 'true' by default.
     * @return Success object.
     */
    public Success verifyChecked(boolean bChecked) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bChecked) {
                if (!control().isSelected())
                    throw new RuntimeException("Control is not selected but selected is expected.");
            } else {
                if (control().isSelected())
                    throw new RuntimeException("Control is selected but not selected is expected.");
            }

        }, this, "");
    }

    /**
     * Method returns out parameter true if checkbox is checked, false is checkbox is not checked.
     *
     * @param skipMessage Log message
     * @return Success object, getCheckedValue - Boolean
     */
    public Success getCheckedAndSkip(String skipMessage) {
        Success Suc = new Success(this);
        getCheckedValue = false;

        try {
            getCheckedValue = control().isSelected();

            Suc.addArgumentsOfMethodForLog("getCheckedValue", getCheckedValue, true);
            Suc.addArgumentsOfMethodForLog("skipMessage", skipMessage, false);
            return Suc.finish(null);
        } catch (Exception ex) {
            return Suc.finish(ex);
        }
    }

    /**
     * Method checks checkbox.
     *
     * @param bCheck           Bool parameter, if set to true, checkbox will be checked, if false, checkbox will be unchecked.
     * @param bCheckWithAction If set to true checkbox will be checked with action.It is false by default.
     * @return Success object
     */
    public Success check(boolean bCheck, boolean bCheckWithAction) {
        return UIReferences.eval().evaluate(() ->
        {
            if (bCheck && control().isSelected())
                throw new RuntimeException("Control is already selected.");
            if (!bCheck && !control().isSelected())
                throw new RuntimeException("Control is already deselected.");

            if (bCheckWithAction)
                UIReferences.actionsBuilder().moveToElement(control()).click().build().perform();
            else
                control().click();

        }, this, "");
    }
}
