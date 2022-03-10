package org.raf3k.apptesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.apptesting.AppReferences;
import org.raf3k.apptesting.basetypes.AppControlBase;
import org.raf3k.shared.logging.Success;

public class MbCheckBox extends AppControlBase {

    public MbCheckBox(String sAlias, By searchByAndroid, By searchByIos) {
        super(sAlias, searchByAndroid, searchByIos);
    }

    public MbCheckBox(AppControlBase parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    public MbCheckBox(WebElement parent, String sAlias, By searchByAndroid, By searchByIos) {
        super(parent, sAlias, searchByAndroid, searchByIos);
    }

    /**
     * Method verifies if the checkbox is selected or not.
     *
     * @param bSelected Set to 'false' if you want to check if checkbox is not selected and vice versa.
     * @return Success object.
     */
    public Success verifySelected(boolean bSelected) {
        return AppReferences.eval().evaluate(() -> {

            if (bSelected && !control().isSelected())
                throw new RuntimeException("Control is not selected but it is expected to be.");

            if (!bSelected && control().isSelected())
                throw new RuntimeException("Control is selected but it is not expected to be.");

        }, this, "");
    }

    /**
     * Method returns boolean based on the checkbox status.
     *
     * @return Boolean value.
     */
    public Boolean getCheckboxStatus() {

        return control().isSelected();
    }

    /**
     * Method checks the checkbox.
     *
     * @param bSelect                Bool parameter, if set to 'true', checkbox will be checked and vice versa.
     * @param bSelectWithTouchAction If set to 'true' checkbox will be checked with touch action.
     * @return Success object.
     */
    public Success select(boolean bSelect, boolean bSelectWithTouchAction) {
        return AppReferences.eval().evaluate(() -> {

            if (bSelect && control().isSelected())
                throw new RuntimeException("Control is already selected.");

            if (!bSelect && !control().isSelected())
                throw new RuntimeException("Control is already deselected.");

            if (bSelectWithTouchAction)
                AppReferences.touchActionsBuilder().singleTap(control()).perform();
            else
                control().click();
        }, this, "");
    }
}
