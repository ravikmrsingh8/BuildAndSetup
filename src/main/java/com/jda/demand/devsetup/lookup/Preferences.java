package com.jda.demand.devsetup.lookup;

import com.jda.demand.devsetup.utils.Constants;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class Preferences {

    public static Preferences instance;
    private Properties properties;


    public boolean isExist() {
        return new File(Constants.PREFERENCES).exists();
    }


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
            if(isExist()){
                properties.load(new FileReader(new File(Constants.PREFERENCES)));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not load preferences");
        }
    }

    public void save() {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(Constants.PREFERENCES);
            properties.store(os, "User Preferences");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    public Set<String> getPropertiesNames() {
        return properties.stringPropertyNames();
    }
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
