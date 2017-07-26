package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.DefaultExecutor;

public class CommandExecutor extends DefaultExecutor {
    private static CommandExecutor executor = new CommandExecutor();
    private CommandExecutor() {}
    public static CommandExecutor getExecutor() {
        return executor;
    }
}
