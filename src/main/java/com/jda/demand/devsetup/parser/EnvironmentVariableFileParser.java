package com.jda.demand.devsetup.parser;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

public class EnvironmentVariableFileParser {
    //private static int _line = 0;
    private static Logger logger = Logger.getLogger(EnvironmentVariableFileParser.class.getName());
    private static Pattern pattern = Pattern.compile("%([^%]{1,}?)%");
    private static Map<String, String> envMap = new LinkedHashMap<>();

    public static Map<String, String> parseFile(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            Pair<String, String> pair = parseLine(line);
            if (pair != null) {
                envMap.put(pair.getKey(), pair.getValue());
            }
        }
        reader.close();
        logger.log(Level.INFO, String.format("Environment Map %s", envMap));
        return envMap;
    }

    private static Pair<String, String> parseLine(String line) {
        //_line++;
        line = line.trim();
        boolean startsWithSET = line.regionMatches(true, 0, "set", 0, 3);

        if (startsWithSET) {
            StringTokenizer tok = new StringTokenizer(line.substring(3), "=");
            String key = tok.nextToken().trim();
            String value = tok.nextToken().trim();
            value = getParsedEnvVarValue(value);
            return new Pair<>(key, value);
        }
        return null;
    }
    private static String getParsedEnvVarValue(String value) {
        Matcher matcher = pattern.matcher(value);
        String envVar = null;
        while(matcher.find()) {
            String groupWithPercentChar = matcher.group();
            String group = groupWithPercentChar.replaceAll("%", "");
            if(envMap.containsKey(group)) {
                value = value.replace(groupWithPercentChar,envMap.get(group));
            } else if( (envVar = getEnvVar(group)) != null) {
                value = value.replace(groupWithPercentChar, envVar);
            }
        }
        return value;
    }
    private static String getEnvVar(String envVar) {
        for(Map.Entry<String, String> entry: System.getenv().entrySet()){
            if(entry.getKey().toUpperCase().equals(envVar.toUpperCase())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
