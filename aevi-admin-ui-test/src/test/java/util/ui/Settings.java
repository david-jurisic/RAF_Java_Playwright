package util.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final Properties props = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("settings.xml")) {
            props.loadFromXML(fis);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load settings.xml file.", ex);
        }
    }

    public static String getSettingsValue(String name) {
        return props.getProperty(name);
    }
}
