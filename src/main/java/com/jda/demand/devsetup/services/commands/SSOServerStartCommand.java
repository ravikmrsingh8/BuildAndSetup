package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class SSOServerStartCommand extends StartCommand {
    private final String EXE = Constants.LAUNCH;
    private final String CIS_HOME = (String) Lookup.getInstance().getVariables().get(Constants.CIS_HOME);
    public SSOServerStartCommand() {
        addArgument(EXE);
        addArgument(Constants.SSO_SERVER_PY);
    }

    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        String workingDirectory = CIS_HOME;
        if(!CIS_HOME.endsWith(_$+"bin")) {
            workingDirectory += _$+"bin";
        }
        return new File(workingDirectory);
    }
}
