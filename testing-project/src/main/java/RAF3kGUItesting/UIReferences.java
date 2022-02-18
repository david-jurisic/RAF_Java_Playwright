package RAF3kGUItesting;

import RAF3kShared.Helpers;
import RAF3kShared.Logging.Evaluator;
import org.openqa.selenium.WebDriver;
import RAF3kGUItesting.WebTesting.WebEngine;

public class UIReferences {
    public static String currentPageContext;
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

    public static Helpers hlpr() {

        if (_Hlpr == null)
            _Hlpr = new Helpers();
        return _Hlpr;
    }

    private static Evaluator _Eval;

    public static Evaluator eval() {
        if (_Eval == null)
            _Eval = new Evaluator(getWebDriver());
        if (_Eval.driver == null)
            _Eval = new Evaluator(getWebDriver());
        return _Eval;
    }
}
