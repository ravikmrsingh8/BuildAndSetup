package com.jda.demand.devsetup.services.commands;


import java.io.File;

public class OpenFolder extends Command {
    private String folderPath;
    public OpenFolder(String folderPath) {
        addArgument("start");
        addArgument(folderPath);
        this.folderPath = folderPath;
    }

    @Override
    public File getWorkingDirectory() {
        return new File(folderPath);
    }

    @Override
    public String toString() {
        return "start " + folderPath;
    }
}
