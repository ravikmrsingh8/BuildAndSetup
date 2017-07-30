package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.DefaultExecutor;

public class CommandExecutor extends DefaultExecutor {

    private CommandExecutor() {}
    public static CommandExecutor getExecutor() {
        return new CommandExecutor();
    }
}
