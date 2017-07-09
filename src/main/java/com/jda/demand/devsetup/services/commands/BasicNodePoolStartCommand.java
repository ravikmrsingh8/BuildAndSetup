package com.jda.demand.devsetup.services.commands;

public class BasicNodePoolStartCommand extends AbstractCommand {

    private final String EXE = "startNodePoolManager.cmd";

    public BasicNodePoolStartCommand() {
        super("cmd");
        addArgument("/c");
        addArgument("start");
        addArgument("Basic Node Pool");
        addArgument("cmd");
        addArgument("/k");
        addArgument(EXE);
        addArgument("Basic");
    }

}

