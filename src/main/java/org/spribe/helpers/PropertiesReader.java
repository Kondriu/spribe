package org.spribe.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;

    public PropertiesReader(String fileName) {
        String basePath = "src/main/resources/";
        String pathToResources = basePath + fileName;
        properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToResources));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(final String key) {
        return properties.getProperty(key);
    }
}
