package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbRadioButton;

public class WbRadioButtonEx extends WbRadioButton {
    public WbRadioButtonEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbRadioButtonEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }
}
