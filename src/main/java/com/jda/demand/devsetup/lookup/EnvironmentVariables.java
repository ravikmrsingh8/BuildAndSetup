package com.jda.demand.devsetup.lookup;

import com.jda.demand.devsetup.parser.EnvironmentVariableFileParser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EnvironmentVariables {

    public  String getVariable(String key) {
        return getMap().get(key);
    }

    public  Map<String, String> getVariables() {
        return getMap();
    }

    public Map<String, String> load(String filePath)  {
        try {
            setMap(EnvironmentVariableFileParser.parseFile(filePath));
        } catch (IOException e){
            throw new RuntimeException("Couldn't load Environment setup file");
        }
        return getMap();
    }


    private Map<String, String> map = new LinkedHashMap<>();
    private Map<String, String> getMap() {
        return map;
    }
    private void setMap(Map<String, String> map) {
        this.map = map;
    }

    private static EnvironmentVariables instance;
    public static EnvironmentVariables getInstance() {
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
