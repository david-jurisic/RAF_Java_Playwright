package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.logging.Success;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.Duration;

public class WbPage extends WebControlBase {
    private URL pageURL;

    public WbPage() {
    }

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
     * @return Success object.
     */
    public Success navigate() {
        return UIReferences.eval().evaluate(() ->
                        UIReferences.getWebDriver().navigate().to(pageURL),
                this, "");
    }

    /**
     * Method verifies the page is displayed by taking driver current URL and checking.
     *
     * @return Success object.
     */
    public Success verifyDisplayed() {
        return UIReferences.eval().evaluate(() ->
        {
            if (!UIReferences.getWebDriver().getCurrentUrl().equals(pageURL.toExternalForm()))
                throw new RuntimeException("Page not displayed");

        }, this, "");
    }

    /**
     * Method verifies the page is displayed by taking driver current URL and checking against the WebPage URL.
     *
     * @param iTime Duration(seconds) that the WebDriver waits for the URL to be contained.
     * @return Success object.
     */
    public Success verifyDisplayed(int iTime) {
        return UIReferences.eval().evaluate(() ->
        {
            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), Duration.ofSeconds(iTime));

            if (!wait.until(ExpectedConditions.urlContains(pageURL.toExternalForm()))) {
                throw new RuntimeException("Page not displayed");
            }

        }, this, "");
    }

    /**
     * Method verifies the page is displayed by taking driver current URL and checking.
     *
     * @return Success object.
     */
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
                sLink = new URI(pageUrl.getProtocol(), pageUrl.getUserInfo(), pageUrl.getHost(), pageUrl.getPort(), pageUrl.getPath(), pageUrl.getQuery(),
                        null).toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if (!UIReferences.getWebDriver().getCurrentUrl().contains(sLink))
                throw new RuntimeException(MessageFormat.format("Web page is not displayed.Searched webpage {0}, current webpage {1}",
                        UIReferences.currentPageContext, UIReferences.getWebDriver().getCurrentUrl()));
        }, this, "");
    }

    /**
     * Method verifies the page is displayed by taking driver current URL and checking.
     *
     * @return boolean
     */
    public boolean booleanVerifyDisplayedByLink() {

        try {
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
                sLink = new URI(pageUrl.getProtocol(), pageUrl.getUserInfo(), pageUrl.getHost(), pageUrl.getPort(), pageUrl.getPath(),
                        pageUrl.getQuery(), null).toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if (!UIReferences.getWebDriver().getCurrentUrl().contains(sLink))
                throw new RuntimeException(MessageFormat.format("Web page is not displayed.Searched webpage {0}, current webpage {1}",
                        UIReferences.currentPageContext, UIReferences.getWebDriver().getCurrentUrl()));

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Method verifies the page is displayed by taking driver current URL and checking and returning bool.
     *
     * @return boolean
     */
    public boolean verifyDisplayedAndReturnBoolean() {
        if (verifyDisplayed().Ex != null)
            return false;
        else
            return true;
    }

    /**
     * Method switches to main tab.
     *
     * @return Succes Object
     */
    public Success switchToMainTab() {
        return UIReferences.eval().evaluate(() ->
        {
            if (UIReferences.getWebDriver().getWindowHandles().stream().count() > 1) {
                UIReferences.getWebDriver().switchTo().window(UIReferences.getWebDriver().getWindowHandles().stream().findFirst().toString());
            }
        }, this, "");
    }

    /**
     * method verifies if URL contains specific chunk of string.
     *
     * @param sChunk string chunk to be verified.
     * @return Success object.
     */
    public Success verifyURLChunk(String sChunk) {
        return UIReferences.eval().evaluate(() ->
        {
            if (!UIReferences.getWebEng().waitForPageLoading())
                throw new RuntimeException();

            if (!UIReferences.getWebDriver().getCurrentUrl().contains(sChunk))
                throw new RuntimeException(MessageFormat.format("Cannot find specific chunk in URL. <br> Current URL: {0} <br> Searched chunk: {1}",
                        UIReferences.getWebDriver().getCurrentUrl(), sChunk));
        }, this, "");
    }
}
