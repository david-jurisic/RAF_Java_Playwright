package org.raf3k.guittesting.webtesting.types;

import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.logging.Success;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;

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

    /**
     * Method activates web pages i.e. navigates driver to certain link.
     *
     * @return Success object
     */
    public Success navigate() {
        return UIReferences.eval().evaluate(() ->
                        UIReferences.getWebDriver().navigate().to(pageURL),
                this, "");
    }

    public Success verifyDisplayed() {
        return UIReferences.eval().evaluate(() ->
        {
            if (!UIReferences.getWebDriver().getCurrentUrl().equals(pageURL.toExternalForm()))
                throw new RuntimeException("Page not displayed");

        }, this, "");
    }


    public Success verifyDisplayedByLink() {
        return UIReferences.eval().evaluate(() ->
        {
            String sMainUrlWithoutApi = UIReferences.getWebDriver().getCurrentUrl();

            if (sMainUrlWithoutApi.contains("?")) {
                String sChunk = sMainUrlWithoutApi.substring(0, sMainUrlWithoutApi.indexOf('?'));

                sMainUrlWithoutApi = sChunk.replace("?", "");
            }

            URL pageUrl = null;

            try {
                pageUrl = new URL(sMainUrlWithoutApi);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String sLink = null;

            try {
                sLink = new URI(pageUrl.getProtocol(),pageUrl.getUserInfo(),pageUrl.getHost(),pageUrl.getPort(),pageUrl.getPath(),pageUrl.getQuery(), null).toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if (!UIReferences.getWebDriver().getCurrentUrl().contains(sLink))
                throw new RuntimeException(MessageFormat.format("Web page is not displayed.Searched webpage {0}, current webpage {1}",
                        UIReferences.currentPageContext, UIReferences.getWebDriver().getCurrentUrl()));
        }, this,"");
    }
}
