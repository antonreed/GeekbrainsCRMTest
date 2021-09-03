package com.antonreed;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfProperties {
    protected static InputStreamReader inputStreamReader;
    protected static Properties PROPERTIES;
    protected static final File CONF_FILE = new File("src/test/resources/conf.properties");

    static {
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(CONF_FILE), StandardCharsets.UTF_8);
            PROPERTIES = new Properties();
            PROPERTIES.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null)
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
