package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.EnvironmentVariables;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

import java.util.Map;


public class CommandExecutor {
    private Executor executor;
    private Map<String, String> environment;
    private Command command;

    public CommandExecutor setCommand(Command command) {
        this.command = command;
        return this;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Map<String, String> getEnvironment() {
        return environment;
    }

    public void setEnvironment(Map<String, String> environment) {
        this.environment = environment;
    }

    public Command getCommand() {
        return command;
    }

    public CommandExecutor() {
        setCommand(null);
        setEnvironment(EnvironmentVariables.getInstance().getVariables());
        setExecutor(new DefaultExecutor());
    }

    public String execute() {
        try {
            getExecutor().setWorkingDirectory(getCommand().getWorkingDirectory());
            getExecutor().execute(getCommand(), getEnvironment(), new DefaultExecuteResultHandler());
        } catch (Exception e) {
            throw new RuntimeException("Command couldn't run", e);
        }
        return command.toString();
    }
}
