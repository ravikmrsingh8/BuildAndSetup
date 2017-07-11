package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManagedServerStartCommand extends Command {
    private final String EXE = Constants.START_WEB_SERVER;
    private final String ADMIN_PORT = Lookup.getInstance().getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT);
    private final String SCPO_HOME = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);
    private final String HOST_NAME = Lookup.getInstance().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME);

    public ManagedServerStartCommand() {
        addArgument(EXE);
        addArgument("JDAServer");
        addArgument("http://" + HOST_NAME + ":" + ADMIN_PORT);
        addArgument("debugsocket");
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
        System.getenv().forEach((key, value) -> {
            envMap.put(key, value);
        });
        Lookup.getInstance().getEnvironmentVariables().forEach((key, value) -> {
            envMap.put(key, value);
        });
        return envMap;
    }
}
