package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;

public class WbDropDownList extends WebControlBase {

    public WbDropDownList() {
    }

    public WbDropDownList(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbDropDownList(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    /**
     * Verifies selected item in the drop-down list.
     *
     * @param sItem Selected item's text to be verified in the drop-down list.
     * @return Success object
     */
    public Success verifySelectedItem(String sItem) {
        return UIReferences.eval().evaluate(() ->
        {
            Select selectList = new Select(control());

            if (!selectList.getFirstSelectedOption().getText().equals(sItem))
                throw new RuntimeException(MessageFormat.format("Selected item: {0} .does not match searched item: {1} .",
                        selectList.getFirstSelectedOption().getText(), sItem));

        }, this, "");
    }

    /**
     * Double clicks dropdown option.
     * @param iIdx Index of option.
     * @return Success object
     */
    public Success doubleClickOnOption(int iIdx) {
        return UIReferences.eval().evaluate(() ->
        {
            Select Select = new Select(control());

            WebElement ctrlOption = Select.getOptions().get(iIdx);
            UIReferences.actionsBuilder().moveToElement(control()).doubleClick(ctrlOption).build().perform();

        }, this, "");
    }
}
