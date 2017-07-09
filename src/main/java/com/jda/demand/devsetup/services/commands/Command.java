package com.jda.demand.devsetup.services.commands;

import java.io.File;

public class Command {
    private String commandName;
    private Class<?> command;
    private File workingDir;

    public Command(String commandName, Class<?> command, File WorkingDir) {
        setCommand(command);
        setCommandName(commandName);
        setWorkingDir(getWorkingDir());
    }

    public Command(String commandName, Class<?> command, String workingDir) {
        this(commandName, command, new File(workingDir));
    }

    public Command(String commandName, Class<?> command) {
        this(commandName, command, new File("."));
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Class<?> getCommand() {
        return command;
    }

    public void setCommand(Class<?> command) {
        this.command = command;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }
}
