package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenCommandPromptCommand extends StartCommand {
    private final String EXE = Constants.START_ADMIN_SERVER;
    private final String SCPO_HOME = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);

    public OpenCommandPromptCommand() {

    }
    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        StringBuilder workingDirectory = new StringBuilder();
        workingDirectory.append(SCPO_HOME + _$);
        workingDirectory.append(Constants.WEBLOGIC +_$);
        workingDirectory.append(Constants.CONFIG + _$);
        workingDirectory.append(Constants.BIN +_$);
        workingDirectory.append(Constants.PLATFORM +_$);
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
