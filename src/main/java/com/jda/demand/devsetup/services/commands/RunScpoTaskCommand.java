package com.jda.demand.devsetup.services.commands;

public class RunScpoTaskCommand extends AbstractCommand {
    private final String EXE = "runScpoTask.cmd";

    public RunScpoTaskCommand() {
        super("cmd");
        addArgument("/C");
        addArgument("start");
        addArgument("Run SCPO Task");
        addArgument("cmd.exe");
        addArgument("/K");
        addArgument(EXE);
        addArgument("generate_configcode");
    }
}
