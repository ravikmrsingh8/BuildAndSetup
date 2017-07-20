package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.components.ToggleSwitch;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.ExecuteException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessExecutionResultHandler extends DefaultExecuteResultHandler {
    private ToggleSwitch aSwitch;
    private String component;
    public static Logger logger  = Logger.getLogger(ProcessExecutionResultHandler.class.getName());

    public ProcessExecutionResultHandler(ToggleSwitch aSwitch, String component) {
        this.aSwitch = aSwitch;
        this.component = component;
    }
    @Override
    public void onProcessFailed(ExecuteException e) {
        aSwitch.switchedOnProperty().setValue(false);
        logger.log(Level.INFO, String.format("Switching off "+component));
    }

    @Override
    public void onProcessComplete(int exitValue) {
        aSwitch.switchedOnProperty().setValue(false);
        logger.log(Level.INFO, String.format("Switching off "+component));
    }
}
