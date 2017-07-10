package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.CommandLine;

import java.io.File;

public abstract class Command extends CommandLine {
    private StringBuilder command = new StringBuilder();

    private StringBuilder getCommand() {
        return command;
    }

    public Command(String executable) {
        super(executable);
        getCommand().append(executable);
        getCommand().append(" ");
    }

    @Override
    public CommandLine addArgument(String command) {
        super.addArgument(command);
        getCommand().append(command);
        getCommand().append(" ");
        return this;
    }


     /* subclasses will override this method*/
    public abstract File getWorkingDirectory();

    @Override
    public String toString() {
        return getCommand().toString();
    }
}
