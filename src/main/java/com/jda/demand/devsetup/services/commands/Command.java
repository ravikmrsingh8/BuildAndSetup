package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.util.Map;

public abstract class Command extends CommandLine {

    public Command() {
        super("cmd");
        super.addArgument("/C");
    }

    public abstract File getWorkingDirectory();

    public Map<String, String> getEnvironmentVariables() {
        return System.getenv();
    }

}
