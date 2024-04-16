package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbIcon;

public class WbIconEx extends WbIcon {
    public WbIconEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbIconEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbIconEx(By searchBy, WebElement parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbIconEx() {
    }
}
