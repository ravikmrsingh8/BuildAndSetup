package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandExecutor {
    Logger logger = Logger.getLogger(getClass().getName());
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
        setEnvironment(Lookup.getInstance().getEnvironmentVariables());
        setExecutor(new DefaultExecutor());
    }

    public String execute() {
        try {
            getExecutor().setWorkingDirectory(getCommand().getWorkingDirectory());
            getExecutor().execute(getCommand(), getCommand().getEnvironmentVariables(), new DefaultExecuteResultHandler());
        } catch (Exception e) {
            throw new RuntimeException("Command couldn't run", e);
        }
        logger.log(Level.INFO, String.format("Running %s",getCommand().toString()));
        return command.toString();
    }
}
