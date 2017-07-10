package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;
import com.sun.jndi.cosnaming.CNCtx;

import java.io.File;

public class SSOServerStartCommand extends Command {
    private final String EXE = Constants.LAUNCH;

    public SSOServerStartCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("SSO Server");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument(Constants.SSO_SERVER_PY);
    }

    @Override
    public File getWorkingDirectory() {
        String CIS_HOME = Preferences.getInstance().getProperty(Constants.CIS_HOME);
        return new File(CIS_HOME);
    }
}
