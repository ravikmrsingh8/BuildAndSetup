package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class SetConfigCodeCommand extends Command {
    private final String EXE = Constants.SET_CONFIG_CODE;
    private final String ORACLE_NET_SERVICE = Lookup.getInstance().getBuildProperties().getProperty(Constants.ORACLE_NET_SERVICE);
    private final String SCPO_HOME = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);
    public SetConfigCodeCommand() {
        addArgument(EXE);
        addArgument(Constants.WWFMGR);
        addArgument(Constants.WWFMGR + "@" + ORACLE_NET_SERVICE);
        addArgument(Constants.SCPOMGR);
        addArgument(Constants.SCPOMGR + "@" + ORACLE_NET_SERVICE);
    }

    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        StringBuilder workingDirectory = new StringBuilder();
        workingDirectory.append(SCPO_HOME + _$);
        workingDirectory.append(Constants.WEBLOGIC + _$);
        workingDirectory.append(Constants.CONFIG + _$);
        workingDirectory.append(Constants.DATABASE + _$);
        workingDirectory.append(Constants.SCPOWEB + _$);
        return new File(workingDirectory.toString());
    }
}
