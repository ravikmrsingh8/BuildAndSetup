package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

import java.io.IOException;

public class CommandExecutor {
    private static Executor executor = new DefaultExecutor();
    public static String execute(String command) {
        CommandLine commandLine = CommandFactory.getCommand(command);
        System.out.println(commandLine);
        try {
            executor.execute(commandLine, new DefaultExecuteResultHandler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commandLine.toString();
    }
}
