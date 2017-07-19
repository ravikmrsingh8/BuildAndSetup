package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class AdminServerStopCommand extends StopCommand {
    private final String EXE = Constants.SHUTDOWN_ADMIN_SERVER;
    private final String SCPO_HOME = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);
    private final String HOST_NAME = Lookup.getInstance().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME);
    private final String ADMIN_PORT = Lookup.getInstance().getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT);

    public AdminServerStopCommand() {
        addArgument(EXE);
        addArgument("t3://" + HOST_NAME + ":" + ADMIN_PORT);
        addArgument(Constants.ADMIN_USER);
        addArgument(Constants.ADMIN_PASSWORD);
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
