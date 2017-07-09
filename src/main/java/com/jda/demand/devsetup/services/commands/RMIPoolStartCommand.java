package com.jda.demand.devsetup.services.commands;

public class RMIPoolStartCommand extends AbstractCommand {
    private final String EXE = "startNodePoolManager.cmd";

    public RMIPoolStartCommand() {
        super("cmd");
        addArgument("/c");
        addArgument("start");
        addArgument("RMI Node Pool");
        addArgument("cmd");
        addArgument("/k");
        addArgument(EXE);
        addArgument("RMI");
    }
}
