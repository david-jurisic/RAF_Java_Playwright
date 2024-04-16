package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbDropDown;

import java.util.List;

public class WbDropDownEx extends WbDropDown {
    public WbDropDownEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbDropDownEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbDropDownEx() {
    }

    public String getControlTextEx() {
        if (this.control().getText() != null || !this.control().getText().equals("Please select...")) {
            return this.control().getText().trim();
        } else {
            return this.control().getAttribute("label") != null ? this.control().getAttribute("label") : this.control().getText();
        }
    }
}
