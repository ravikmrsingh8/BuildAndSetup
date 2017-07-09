package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.properties.Preferences;
import com.jda.demand.devsetup.utils.Constants;
import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicNodePoolStartCommand extends CommandLine {

    private StringBuilder command = new StringBuilder("cmd ");

    public StringBuilder getCommand() {
        return command;
    }

    public BasicNodePoolStartCommand() {
        super("cmd");
        addArgument("/c");
        addArgument("start");
        addArgument("Basic Node Pool");
        addArgument("cmd");
        addArgument("/k");

        String SCPO_HOME = Preferences.getInstance().getProperty(Constants.SCPO_HOME);
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SCPO_HOME:"+SCPO_HOME);
        if (SCPO_HOME == null) {
            throw new RuntimeException("SCPO Home not set");
        }
        String _$ = File.separator;
        String EXE_PATH = SCPO_HOME + _$ + "weblogic" + _$ + "config" + _$ + "bin" + _$ + "platform"+ _$+"startNodePoolManager" ;
        addArgument(EXE_PATH);
        addArgument("Basic");
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

