package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;
import java.util.Map;

public class RMIPoolStartCommand extends Command {
    private final String EXE = Constants.START_NODE_POOL;
    private final String SCPO_HOME = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);

    public RMIPoolStartCommand() {
        addArgument(EXE);
        addArgument(Constants.RMI_NODE_POOL);
    }

    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        StringBuilder workingDirectory = new StringBuilder();
        workingDirectory.append(SCPO_HOME + _$);
        workingDirectory.append(Constants.WEBLOGIC + _$);
        workingDirectory.append(Constants.CONFIG + _$);
        workingDirectory.append(Constants.BIN + _$);
        workingDirectory.append(Constants.PLATFORM + _$);
        return new File(workingDirectory.toString());
    }


}
