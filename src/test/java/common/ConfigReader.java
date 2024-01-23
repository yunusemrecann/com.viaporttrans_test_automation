package common;

import common.logs.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;

    static {
        String path = "src/test/resources/configuration.properties";

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            Log.warn("The file could not be read.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
