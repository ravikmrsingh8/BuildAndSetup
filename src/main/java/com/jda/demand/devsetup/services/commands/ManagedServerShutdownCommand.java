package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManagedServerShutdownCommand extends StopCommand {
    private final String EXE = Constants.STOP_WEB_SERVER;
    private final String ADMIN_PORT = Lookup.getInstance().getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT);
    private final String SCPO_HOME = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);
    private final String HOST_NAME = Lookup.getInstance().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME);

    public ManagedServerShutdownCommand() {
        addArgument(EXE);
        addArgument("t3://" + HOST_NAME + ":" + ADMIN_PORT);
        addArgument(Constants.ADMIN_USER);
        addArgument(Constants.ADMIN_PASSWORD);
        addArgument(Constants.WEBWORKS);
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

    @Override
    public Map<String, String> getEnvironmentVariables() {
        Map<String, String> envMap = new LinkedHashMap<>();
        envMap.putAll(System.getenv());
        envMap.putAll(Lookup.getInstance().getEnvironmentVariables());
        return envMap;
    }
}
