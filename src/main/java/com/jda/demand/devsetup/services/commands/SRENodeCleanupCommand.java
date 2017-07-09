package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

public class SRENodeCleanupCommand extends AbstractCommand {
    private final String EXE = "sqlplus";
    private final String ORACLE_NET_SERVICE = Preferences.getInstance().getProperty(Constants.ORACLE_NET_SERVICE);

    public SRENodeCleanupCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Clear Node Pool Queue");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument("WWFMGR/WWFMGR@" + ORACLE_NET_SERVICE);
        addArgument("@Cleanup.sql");
    }
}
