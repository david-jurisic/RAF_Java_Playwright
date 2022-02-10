package root;

import org.openqa.selenium.WebDriver;

public class UIReferences {
    public static String CurrentPageContext;
    private static WebDriver _WebDriver;

    public static WebDriver getWebDriver() {
        return _WebDriver;
    }

    public static void setWebDriver(WebDriver newWebDriver) {
        _WebDriver = newWebDriver;
    }

    private static WebEngine _WebEng;

    public static WebEngine getWebEng() {
        if (_WebEng == null)
            _WebEng = new WebEngine();
        return _WebEng;
    }

    private static Helpers _Hlpr;
    public static Helpers Hlpr() {

        if (_Hlpr == null)
            _Hlpr = new Helpers();
        return _Hlpr;
    }
}
