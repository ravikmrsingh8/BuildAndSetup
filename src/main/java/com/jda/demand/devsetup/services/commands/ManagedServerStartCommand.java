package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

public class ManagedServerStartCommand extends AbstractCommand {
    private final String EXE = "startManagedWebworksServer.cmd";
    private final String ADMIN_PORT = Preferences.getInstance().getProperty(Constants.SERVER_ADMIN_PORT);

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
}
