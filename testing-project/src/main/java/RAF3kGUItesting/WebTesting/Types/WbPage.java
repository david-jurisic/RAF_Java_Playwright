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
    private URL PageURL;

    public URL getPageURL() {
        return PageURL;
    }

    public void setPageURL(URL newPageUrl) {
        PageURL = newPageUrl;
    }

    public WbPage(String sPath, String sAlias)
    {
        try {
            this.setPageURL(new URL(new URL(UIReferences.CurrentPageContext), sPath));
            this.setsAlias(sAlias);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.setsAlias(sAlias);
    }

    public Success Navigate() {
        return UIReferences.Eval().Evaluate(() -> UIReferences.getWebDriver().navigate().to(PageURL),this,"");
    }
}
