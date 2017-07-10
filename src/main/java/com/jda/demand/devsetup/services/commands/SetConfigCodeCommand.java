package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.BuildProperties;
import com.jda.demand.devsetup.lookup.EnvironmentVariables;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class SetConfigCodeCommand extends Command {
    private final String EXE = Constants.SET_CONFIG_CODE;
    private final String ORACLE_NET_SERVICE = BuildProperties.getInstance().getProperty(Constants.ORACLE_NET_SERVICE);

    public SetConfigCodeCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Set Config Code");
        addArgument("cmd.exe");
        addArgument("/K");
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
        workingDirectory.append(EnvironmentVariables.getInstance().getVariable(Constants.ENV_BUILD_ROOT) + _$);
        workingDirectory.append(Constants.WEBLOGIC + _$);
        workingDirectory.append(Constants.CONFIG + _$);
        workingDirectory.append(Constants.DATABASE + _$);
        workingDirectory.append(Constants.SCPOWEB + _$);
        return new File(workingDirectory.toString());
    }
}
