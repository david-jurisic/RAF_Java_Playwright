package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbDropDownList;

public class WbDropDownListEx extends WbDropDownList {
    public WbDropDownListEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbDropDownListEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
