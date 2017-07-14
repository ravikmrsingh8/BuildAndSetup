package com.jda.demand.devsetup.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.Map;

public class EnvironmentVariableParserTest extends TestCase {
    public EnvironmentVariableParserTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(EnvironmentVariableParserTest.class);
    }

    public void testParser() {
        try {
            Map<String, String> map = EnvironmentVariableFileParser.parseFile("C:\\src\\rel901\\build901\\setEnv.bat");
            map.forEach((key, value) ->{
                System.out.println("["+key+":"+value+"]");
                if(value.contains("%")) {
                    assertFalse(true);
                }
            });
            assertTrue(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
