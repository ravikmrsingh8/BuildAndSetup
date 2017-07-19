package com.jda.demand.devsetup.services.commands;

import org.apache.commons.exec.CommandLine;

public abstract class StartCommand extends Command {
    private StringBuilder command = new StringBuilder();

    private StringBuilder getCommand() {
        return command;
    }

    public StartCommand() {
        super.addArgument("start");
        super.addArgument("cmd.exe");
        super.addArgument("/K");
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
