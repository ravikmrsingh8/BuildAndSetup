package com.jda.demand.devsetup.lookup;

import com.jda.demand.devsetup.parser.EnvironmentVariableFileParser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EnvironmentVariables {

    public static String getVariable(String key) {
        return getInstance().getMap().get(key);
    }

    public static Map<String, String> getVariables() {
        return getInstance().getMap();
    }

    public Map<String, String> load(String filePath) throws IOException {
        setMap(EnvironmentVariableFileParser.parseFile(filePath));
        return getMap();
    }


    private static EnvironmentVariables instance;
    private Map<String, String> map = new LinkedHashMap<>();

    private Map<String, String> getMap() {
        return map;
    }

    private void setMap(Map<String, String> map) {
        this.map = map;
    }

    private static EnvironmentVariables getInstance() {
        if (instance == null) {
            synchronized (EnvironmentVariables.class) {
                if (instance == null) {
                    instance = new EnvironmentVariables();
                }
            }
        }
        return instance;
    }
}
