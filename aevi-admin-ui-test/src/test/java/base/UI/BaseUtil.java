package base.ui;

import org.openqa.selenium.WebDriver;
import util.ui.Settings;

public abstract class BaseUtil {
    public static WebDriver driver = null;

    public static final String WEBPAGE_URL = Settings.getSettingsValue("webpage_url");
    public static final String CHROME_DRIVER_LOCATION = Settings.getSettingsValue("chrome_driver_location");
}
