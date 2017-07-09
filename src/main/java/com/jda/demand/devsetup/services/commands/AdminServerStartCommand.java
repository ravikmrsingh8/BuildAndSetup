package com.jda.demand.devsetup.services.commands;


public class AdminServerStartCommand extends AbstractCommand {

    private final String EXE = "startWebworksAdminServer.cmd";

    public AdminServerStartCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Admin Server");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
    }
}
