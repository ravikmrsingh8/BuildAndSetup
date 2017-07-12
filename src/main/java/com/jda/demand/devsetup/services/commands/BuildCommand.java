package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class BuildCommand extends Command {
    private final String EXE = Constants.BUILD;
    private final String SCPO_HOME = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);

    public BuildCommand() {
        addArgument(EXE);
    }

    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        StringBuilder workingDirectory = new StringBuilder();
        workingDirectory.append(SCPO_HOME + _$);
        workingDirectory.append(Constants.BUILD + _$);
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
