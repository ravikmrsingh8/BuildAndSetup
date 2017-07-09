package com.jda.demand.devsetup.lookup;

import com.jda.demand.devsetup.utils.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Preferences {

    public static Preferences instance;
    private Properties properties;

    private Preferences() {
        properties = new Properties();
    }

    public static Preferences getInstance() {
        if (instance == null) {
            synchronized (Preferences.class) {
                if (instance == null) {
                    instance = new Preferences();
                }
            }
        }
        return instance;
    }


    public void load() {
        try {
            File file = new File(Constants.PREFERENCES);
            //Create a new preferences.lookup file
            if (!file.exists()) {
                file = createPreferences();
            }
            //Load Properties
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException("Could not load preferences");
        }
    }

    private File createPreferences() throws IOException {
        FileOutputStream os = new FileOutputStream(Constants.PREFERENCES);
        Properties properties = new Properties();
        properties.store(os, "User Preferences");
        return new File(Constants.PREFERENCES);
    }

    public void save() throws IOException {
        FileOutputStream os = new FileOutputStream(Constants.PREFERENCES);
        properties.store(os, "User Preferences");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
