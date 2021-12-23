package base.UI;

import hook.UI.BaseHooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseUtil {
    public static WebDriver driver;

    public BaseUtil() {
        this.driver = BaseHooks.driver;

        PageFactory.initElements(driver,this);
    }
}
