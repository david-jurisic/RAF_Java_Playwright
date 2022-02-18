package RAF3kGUItesting.WebTesting.Types;

import RAF3kGUItesting.WebTesting.BaseTypes.WebControlBase;
import RAF3kGUItesting.UIReferences;
import RAF3kShared.Logging.Success;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

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
