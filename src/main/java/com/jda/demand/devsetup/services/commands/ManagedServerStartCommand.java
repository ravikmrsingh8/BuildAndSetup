package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.BuildProperties;
import com.jda.demand.devsetup.lookup.EnvironmentVariables;
import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class ManagedServerStartCommand extends Command {
    private final String EXE = Constants.START_WEB_SERVER;
    private final String ADMIN_PORT = BuildProperties.getInstance().getProperty(Constants.SERVER_ADMIN_PORT);

    public ManagedServerStartCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Managed Server");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument("JDAServer");
        addArgument("http://localhost:" + ADMIN_PORT);
        addArgument("debugsocket");
    }

    @Override
    public File getWorkingDirectory() {
        String _$ = File.separator;
        StringBuilder workingDirectory = new StringBuilder();
        workingDirectory.append(EnvironmentVariables.getInstance().getVariable(Constants.ENV_BUILD_ROOT) + _$);
        workingDirectory.append(Constants.WEBLOGIC +_$);
        workingDirectory.append(Constants.CONFIG + _$);
        workingDirectory.append(Constants.BIN +_$);
        workingDirectory.append(Constants.PLATFORM +_$);
        return new File(workingDirectory.toString());
    }
}
