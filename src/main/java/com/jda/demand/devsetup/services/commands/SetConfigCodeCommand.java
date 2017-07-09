package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;

public class SetConfigCodeCommand extends AbstractCommand {
    private final String EXE = "set_config_code.bat";
    private final String ORACLE_NET_SERVICE = Preferences.getInstance().getProperty(Constants.ORACLE_NET_SERVICE);

    public SetConfigCodeCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Set Config Code");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument("WWFMGR");
        addArgument("WWFMGR@" + ORACLE_NET_SERVICE);
        addArgument("SCPOMGR");
        addArgument("SCPOMGR@" + ORACLE_NET_SERVICE);
    }
}
