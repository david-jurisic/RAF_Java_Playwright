package root;

import java.net.MalformedURLException;
import java.net.URL;

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.setsAlias(sAlias);
    }

    public void Navigate() {
        UIReferences.getWebDriver().navigate().to(PageURL);
    }
}
