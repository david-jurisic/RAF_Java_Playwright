package base.UI;

import org.openqa.selenium.WebDriver;
import util.UI.AdminUtil;

public class BaseUtil {
    public static WebDriver driver = null;

    public static final String WEBPAGE_URL = AdminUtil.GetSettingsValue("webpage_url");
    public static final String CHROME_DRIVER_LOCATION = AdminUtil.GetSettingsValue("chrome_driver_location");
}
