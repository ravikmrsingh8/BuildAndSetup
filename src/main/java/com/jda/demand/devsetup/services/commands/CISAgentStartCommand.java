package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class CISAgentStartCommand extends Command {

    private final String EXE = Constants.LAUNCH;

    public CISAgentStartCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("CIS Agent");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument(Constants.CIS_AGENT_PY);
    }

    @Override
    public File getWorkingDirectory() {
        String CIS_HOME = Preferences.getInstance().getProperty(Constants.CIS_HOME);
        return new File(CIS_HOME);
    }
}
