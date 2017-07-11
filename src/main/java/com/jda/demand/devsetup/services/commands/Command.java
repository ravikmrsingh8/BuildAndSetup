package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.util.Map;

public abstract class Command extends CommandLine {
    private StringBuilder command = new StringBuilder();
    private StringBuilder getCommand() {
        return command;
    }

    public Command() {
        super("cmd");
        super.addArgument("/C");
        super.addArgument("start");
        super.addArgument("cmd.exe");
        super.addArgument("/K");
    }

    @Override
    public CommandLine addArgument(String argument) {
        CommandLine cmdline = super.addArgument(argument);
        getCommand().append(argument);
        getCommand().append(" ");
        return cmdline;
    }

    public abstract File getWorkingDirectory();

    public Map<String,String> getEnvironmentVariables(){
        return System.getenv();
    }

    @Override
    public String toString() {
        return getCommand().toString();
    }
}
