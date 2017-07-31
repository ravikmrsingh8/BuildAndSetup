package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class SetConfigCodeCommand extends StartCommand {
    private final String EXE = Constants.SET_CONFIG_CODE;
    private final String ORACLE_NET_SERVICE = getLookup().getBuildProperties().getProperty(Constants.ORACLE_NET_SERVICE);
    private final String SCPO_HOME = getLookup().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);
    private final String USER1 = getLookup().getBuildProperties().getProperty(Constants.USER1);
    private final String USER2 = getLookup().getBuildProperties().getProperty(Constants.USER2);
    private final String USER1_PASS = getLookup().getBuildProperties().getProperty(Constants.USER1_PASS);
    private final String USER2_PASS = getLookup().getBuildProperties().getProperty(Constants.USER2_PASS);

    public SetConfigCodeCommand() {
        addArgument(EXE);
        addArgument(USER2);
        addArgument(USER2_PASS + "@" + ORACLE_NET_SERVICE);
        addArgument(USER1);
        addArgument(USER1_PASS + "@" + ORACLE_NET_SERVICE);
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

    private Lookup getLookup() {
        return Lookup.getInstance();
    }
}
