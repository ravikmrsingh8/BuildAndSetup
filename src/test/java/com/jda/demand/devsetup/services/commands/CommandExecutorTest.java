package com.jda.demand.devsetup.services.commands;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

public class CommandExecutorTest extends TestCase {

    public CommandExecutorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CommandExecutorTest.class);
    }

    public void testExecute() {
        assertTrue(true);
    }
}
