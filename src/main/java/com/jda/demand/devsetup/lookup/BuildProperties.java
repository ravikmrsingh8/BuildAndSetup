package com.jda.demand.devsetup.lookup;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BuildProperties {
    private static BuildProperties instance;
    private Properties properties;
    private static String filePath;

    private BuildProperties() {
        properties = new Properties();
    }

    public static BuildProperties getInstance() {
        if (instance == null) {
            synchronized (BuildProperties.class) {
                if (instance == null) {
                    instance = new BuildProperties();
                }
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void load(String filePath) {
        try {
            properties.load(new FileReader(filePath));
        } catch(IOException e) {
            throw new RuntimeException("Couldn't load "+filePath);
        }

    }
}
