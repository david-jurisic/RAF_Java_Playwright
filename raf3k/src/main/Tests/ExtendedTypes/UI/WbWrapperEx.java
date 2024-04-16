package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbWrapper;
import org.raf3k.shared.logging.Success;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WbWrapperEx extends WbWrapper {
    public WbWrapperEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbWrapperEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbWrapperEx() {
    }
}
