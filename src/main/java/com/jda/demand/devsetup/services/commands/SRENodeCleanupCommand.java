package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.BuildProperties;
import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;

public class SRENodeCleanupCommand extends Command {
    private final String EXE = Constants.SQL_PLUS;
    private final String ORACLE_NET_SERVICE = BuildProperties.getInstance().getProperty(Constants.ORACLE_NET_SERVICE);

    public SRENodeCleanupCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Clear Node Pool Queue");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument("WWFMGR/WWFMGR@" + ORACLE_NET_SERVICE);
        addArgument("@"+Constants.SRE_CLEANUP_SQL);
    }


    @Override
    public File getWorkingDirectory() {
        return new File(".");
    }
}
