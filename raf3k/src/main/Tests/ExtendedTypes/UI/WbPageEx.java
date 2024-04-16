package ExtendedTypes.UI;

import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.types.WbPage;
import org.raf3k.shared.logging.Success;

public class WbPageEx extends WbPage {

    public WbPageEx(String sPath, String sAlias) {
        super(sPath, sAlias);
    }

    public WbPageEx() {
    }

    public Success refreshPage() {
        return UIReferences.eval().evaluate(() -> {
            UIReferences.getWebDriver().navigate().refresh();
        }, this, "");
    }
}
