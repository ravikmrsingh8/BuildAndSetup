package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commandMap = new HashMap<>();

    private CommandFactory() {
    }


    public static Command getCommand(String cmd) {
        Command command = null;
        command = (Command) getCommandMap().get(cmd);
        return command;
    }


    private static Map<String, Command> getCommandMap() {
        return commandMap;
    }

    //Mappings for Command
    static {
        commandMap.put(Constants.START_ADMIN_SERVER, new Command(Constants.START_ADMIN_SERVER, AdminServerStartCommand.class));
        commandMap.put(Constants.START_WEB_SERVER, new Command(Constants.START_WEB_SERVER, ManagedServerStartCommand.class));
        commandMap.put(Constants.START_CIS_AGENT, new Command(Constants.START_CIS_AGENT, CISAgentStartCommand.class));
        commandMap.put(Constants.START_SSO_SERVER, new Command(Constants.START_SSO_SERVER, SSOServerStartCommand.class));
        commandMap.put(Constants.START_BASIC_NODE_POOL, new Command(Constants.START_BASIC_NODE_POOL, BasicNodePoolStartCommand.class));
        commandMap.put(Constants.START_RMI_NODE_POOL, new Command(Constants.START_RMI_NODE_POOL, RMIPoolStartCommand.class));

        commandMap.put(Constants.INSTALL_LIC, new Command(Constants.INSTALL_LIC, InstallLicenseCommand.class));
        commandMap.put(Constants.RUN_SCPO_TASK, new Command(Constants.RUN_SCPO_TASK, RunScpoTaskCommand.class));
        commandMap.put(Constants.SET_CONFIG_CODE, new Command(Constants.SET_CONFIG_CODE, SetConfigCodeCommand.class));
        commandMap.put(Constants.SRE_CLEANUP, new Command(Constants.SRE_CLEANUP, SRENodeCleanupCommand.class));

    }
}
