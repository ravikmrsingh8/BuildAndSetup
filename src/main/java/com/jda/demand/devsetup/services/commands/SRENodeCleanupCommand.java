package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SRENodeCleanupCommand extends Command {
    private final String EXE = Constants.SQL_PLUS;
    private final String ORACLE_NET_SERVICE = Lookup.getInstance().getBuildProperties().getProperty(Constants.ORACLE_NET_SERVICE);

    public SRENodeCleanupCommand() {
        addArgument(EXE);
        addArgument(Constants.WWFMGR + "/" + Constants.WWFMGR + "@" + ORACLE_NET_SERVICE);
        addArgument("@" +Constants.SRE_CLEANUP_SQL );
    }


    @Override
    public File getWorkingDirectory() {
        String classPath = getClass().getResource("/"+Constants.SRE_CLEANUP_SQL).getPath().replace(Constants.SRE_CLEANUP_SQL,"");
        Logger.getLogger(getClass().getName()).log(Level.INFO, classPath);
        return new File(classPath);
    }
}
