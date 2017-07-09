package com.jda.demand.devsetup.services.commands;

import org.apache.commons.exec.CommandLine;


public class AdminServerStartCommand extends CommandLine {

    private StringBuilder command = new StringBuilder("cmd ");
    private StringBuilder getCommand() {
        return command;
    }

    public AdminServerStartCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Admin Server");
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
