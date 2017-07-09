package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

public class InstallLicenseCommand extends AbstractCommand {
    private final String EXE = "installLicense.cmd";
    private final String LIC_FILE = Preferences.getInstance().getProperty(Constants.LIC_FILE);

    public InstallLicenseCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Install SCPO License");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument(LIC_FILE);
    }
}
