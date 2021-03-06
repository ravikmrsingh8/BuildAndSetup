package com.jda.demand.devsetup.lookup;

import com.jda.demand.devsetup.parser.EnvironmentVariableFileParser;
import com.jda.demand.devsetup.utils.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lookup {
    private static Logger logger = Logger.getLogger(Lookup.class.getName());
    private Map<String, Object> variables;
    private Properties buildProperties;
    private Map<String, String> environmentVariables;

    public Properties getBuildProperties() {
        return buildProperties;
    }

    private void setBuildProperties(Properties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public Map<String, String> getEnvironmentVariables() {
        return environmentVariables;
    }

    private void setEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    private void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    //Load lookup based on provided env file
    public void load(final String envfile) {
        try {
            getVariables().put(Constants.ENV_FILE,envfile);
            setEnvironmentVariables(EnvironmentVariableFileParser.parseFile(envfile));
            String buildPropFilePath = getEnvironmentVariables().get(Constants.ENV_BUILD_PROPS);
            logger.log(Level.INFO, String.format("Loading Build Properties file at %s",buildPropFilePath));
            getBuildProperties().load(new FileReader(buildPropFilePath));
            getVariables().put(Constants.LIC_FILE,getBuildProperties().getProperty(Constants.LICENSE_FILE));
            logger.log(Level.INFO, getBuildProperties().toString());

        } catch (IOException e) {
            throw new RuntimeException("Couldn't load Environment Variables setup file");
        }
    }

    private static Lookup instance;
    private Lookup(){
        setBuildProperties(new Properties());
        setEnvironmentVariables(new HashMap<>());
        setVariables(new HashMap<>());
    }
    public static Lookup getInstance(){
        if(instance == null) {
            synchronized (Lookup.class) {
                if(instance == null) {
                    instance = new Lookup();
                }
            }
        }
        return instance;
    }
}
