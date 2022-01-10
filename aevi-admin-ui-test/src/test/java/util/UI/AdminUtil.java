package util.UI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AdminUtil {
    public static String GetSettingsValue(String name)
    {
        String settingsValue = null;
        try
        {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("settings.xml");
            props.loadFromXML(fis);
            fis.close();

            settingsValue = props.getProperty(name);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        return settingsValue;
    }
}
