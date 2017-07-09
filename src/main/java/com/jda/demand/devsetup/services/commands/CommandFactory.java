package com.jda.demand.devsetup.services.commands;

import com.jda.demand.devsetup.properties.Preferences;
import com.jda.demand.devsetup.utils.Constants;
import org.apache.commons.exec.CommandLine;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Class<?>> commandMap = new HashMap<>();

    private CommandFactory(){
    }

    public static CommandLine getCommand(String command){
        CommandLine commandLine = null;
        try {
            commandLine = (CommandLine) commandMap.get(command).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {

        }
        return commandLine;
    }

    //Mappings for Command
    static {

        commandMap.put(Constants.START_ADMIN_SERVER,AdminServerStartCommand.class);
        commandMap.put(Constants.START_BASIC_NODE_POOL,BasicNodePoolStartCommand.class);
    }

}
