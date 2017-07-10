package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.EnvironmentVariables;
import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class InstallLicenseCommand extends Command {
    private final String EXE = Constants.INSTALL_LIC;
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

    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        StringBuilder workingDirectory = new StringBuilder();
        workingDirectory.append(EnvironmentVariables.getInstance().getVariable(Constants.ENV_BUILD_ROOT) + _$);
        workingDirectory.append(Constants.WEBLOGIC +_$);
        workingDirectory.append(Constants.CONFIG + _$);
        workingDirectory.append(Constants.BIN +_$);
        workingDirectory.append(Constants.PLATFORM +_$);
        return new File(workingDirectory.toString());
    }
}
