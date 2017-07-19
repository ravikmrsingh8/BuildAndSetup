package com.jda.demand.devsetup.services.commands;

import org.apache.commons.exec.CommandLine;

public abstract class StopCommand extends Command {
    private StringBuilder command = new StringBuilder();

    private StringBuilder getCommand() {
        return command;
    }

    public StopCommand() {

    }

    @Override
    public CommandLine addArgument(String argument) {
        super.addArgument(argument);
        getCommand().append(argument);
        getCommand().append(" ");
        return this;
    }

    @Override
    public String toString() {
        return getCommand().toString();
    }
}
