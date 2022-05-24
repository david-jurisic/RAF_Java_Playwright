package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

public class WbRadioButton extends WebControlBase {


    public WbRadioButton(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbRadioButton(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Verifies if radio button is selected or not.
     *
     * @param bSelected Set to 'false' if you want to check if radio button is not selected.It is 'true' by default.
     * @return Success object.
     */
    public Success verifySelected(boolean bSelected) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            if (bSelected && !control().isSelected())
                throw new RuntimeException("Control is not selected but selected is expected.");

            if (!bSelected && control().isSelected())
                throw new RuntimeException("Control is selected but not selected is expected.");

        }, this, "");
    }

    /**
     * Method selects or deselects radio button.
     *
     * @param bSelect If set to true, method selects radio button, deselects otherwise.
     * @return Success object.
     */
    public Success select(boolean bSelect) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            if (control().isSelected() && bSelect)
                throw new RuntimeException("Control is already selected.");

            if (!control().isSelected() && !bSelect)
                throw new RuntimeException("Control is already not selected.");

            control().click();

        }, this, "");
    }
}
