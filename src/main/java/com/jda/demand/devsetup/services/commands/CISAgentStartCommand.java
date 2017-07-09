package com.jda.demand.devsetup.services.commands;

public class CISAgentStartCommand extends AbstractCommand {

    private final String EXE = "launch.bat";

    public CISAgentStartCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("CIS Agent");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument("runCISAgent.py");
    }
}
