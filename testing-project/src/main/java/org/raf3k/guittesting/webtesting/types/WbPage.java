package org.raf3k.guittesting.webtesting.types;

import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.logging.Success;

import java.net.MalformedURLException;
import java.net.URL;

public class WbPage extends WebControlBase {
    private URL pageURL;

    public URL getPageURL() {
        return pageURL;
    }

    public void setPageURL(URL newPageUrl) {
        pageURL = newPageUrl;
    }

    public WbPage(String sPath, String sAlias) {
        try {
            this.setPageURL(new URL(new URL(UIReferences.currentPageContext), sPath));
            this.sAlias = sAlias;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.sAlias = sAlias;
    }

    public Success Navigate() {
        return UIReferences.eval().evaluate(() -> UIReferences.getWebDriver().navigate().to(pageURL), this, "");
    }
}
