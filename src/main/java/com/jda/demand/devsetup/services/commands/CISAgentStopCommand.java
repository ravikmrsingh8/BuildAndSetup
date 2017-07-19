package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class CISAgentStopCommand extends StopCommand {

    private final String EXE = Constants.LAUNCH;
    private final String CIS_HOME = (String) Lookup.getInstance().getVariables().get(Constants.CIS_HOME);

    public CISAgentStopCommand() {
        addArgument(EXE);
        addArgument(Constants.STOP_CIS_AGENT_PY);
    }

    @Override
    public File getWorkingDirectory() {
        return new File(CIS_HOME);
    }

}
