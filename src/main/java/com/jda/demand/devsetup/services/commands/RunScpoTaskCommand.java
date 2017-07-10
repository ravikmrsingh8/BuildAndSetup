package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.EnvironmentVariables;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class RunScpoTaskCommand extends Command {
    private final String EXE = Constants.RUN_SCPO_TASK;

    public RunScpoTaskCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Run SCPO Task");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument(Constants.GENERATE_CONFIG_CODE);
    }

    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        StringBuilder workingDirectory = new StringBuilder();
        workingDirectory.append(EnvironmentVariables.getInstance().getVariable(Constants.ENV_BUILD_ROOT) + _$);
        workingDirectory.append(Constants.WEBLOGIC +_$);
        workingDirectory.append(Constants.CONFIG + _$);
        workingDirectory.append(Constants.BIN +_$);
        workingDirectory.append(Constants.SCPOWEB +_$);
        return new File(workingDirectory.toString());
    }
}
