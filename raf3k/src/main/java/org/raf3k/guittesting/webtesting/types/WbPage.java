package org.raf3k.guittesting.webtesting.types;

import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.logging.Success;

import java.net.MalformedURLException;
import java.net.URL;

public class WbPage extends WebControlBase {
    private URL pageURL;

    public WbPage(String sPath, String sAlias) {
        try {
            pageURL = new URL(new URL(UIReferences.currentPageContext), sPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.sAlias = sAlias;
    }

    public Success navigate() {
        return UIReferences.eval().evaluate(() -> UIReferences.getWebDriver().navigate().to(pageURL), this, "");
    }
}
