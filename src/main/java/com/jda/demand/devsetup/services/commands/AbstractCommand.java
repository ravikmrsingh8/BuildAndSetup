package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.CommandLine;

public abstract class AbstractCommand extends CommandLine {
    private StringBuilder command = new StringBuilder();

    private StringBuilder getCommand() {
        return command;
    }

    public AbstractCommand(String executable) {
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


    @Override
    public String toString() {
        return getCommand().toString();
    }
}
