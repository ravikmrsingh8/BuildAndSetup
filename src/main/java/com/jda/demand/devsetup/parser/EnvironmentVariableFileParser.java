package com.jda.demand.devsetup.parser;


import com.jda.demand.devsetup.utils.Constants;
import javafx.util.Pair;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvironmentVariableFileParser {
    private static int _line = 0;
    private static Logger logger = Logger.getLogger(EnvironmentVariableFileParser.class.getName());

    public static Map<String, String> parseFile(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Map<String, String> envMap = new LinkedHashMap<>();

        String line = null;
        while ((line = reader.readLine()) != null) {
            Pair<String, String> pair = parseLine(line);
            if (pair != null) {
                envMap.put(pair.getKey(), pair.getValue());
            }
        }

        String SCPO_HOME = envMap.get(Constants.ENV_BUILD_ROOT);
        String BUILD_ROOT = "%" + Constants.ENV_BUILD_ROOT + "%";
        envMap.forEach((key, value)->{
            if(value.contains(BUILD_ROOT)) {
                envMap.put(key,value.replace(BUILD_ROOT, SCPO_HOME));
            }
        });
        logger.log(Level.INFO, String.format("Environment Map %s", envMap));
        return envMap;
    }

    private static Pair<String, String> parseLine(String line) {
        _line++;
        line = line.trim();
        boolean startsWithSET = line.regionMatches(true, 0, "set", 0, 3);

        if (startsWithSET) {
            StringTokenizer tok = new StringTokenizer(line.substring(3), "=");
            String key = tok.nextToken().trim();
            String value = tok.nextToken().trim();
            return new Pair(key, value);
        }
        return null;
    }
}
