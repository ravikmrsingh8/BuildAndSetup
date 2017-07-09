package com.jda.demand.devsetup.services.commands;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

import java.io.File;


public class CommandExecutor {
    private static Executor executor = new DefaultExecutor();

    public static String execute(String cmd) {
        Command command = CommandFactory.getCommand(cmd);

        CommandLine cmdLine = null;
        try {
            cmdLine = (CommandLine) command.getCommand().newInstance();
            File workingDir = command.getWorkingDir() == null ? new File(".") : command.getWorkingDir();
            executor.setWorkingDirectory(workingDir);
            executor.execute(cmdLine, new DefaultExecuteResultHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmdLine.toString();
    }
}
