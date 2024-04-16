package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbEdit;
import org.raf3k.shared.logging.Success;


public class WbEditEx extends WbEdit {
    public WbEditEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbEditEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbEditEx() {
    }

    public String getControlTextEx() {
        this.exists();
        return this.control().getAttribute("value").trim() != null ? this.control().getAttribute("value").trim() : this.control().getText();
    }

    public Success sendEnter() {
        return UIReferences.eval().evaluate(() -> {
            this.exists();
                this.control().sendKeys(new CharSequence[]{Keys.ENTER});
        }, this, "");
    }
}
