package com.jda.demand.devsetup.services.commands;

public class SSOServerStartCommand extends AbstractCommand {
    private final String EXE = "launch.bat";

    public SSOServerStartCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("SSO Server");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument("runSSOServer.py");
    }
}
