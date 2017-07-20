package com.jda.demand.devsetup.services.commands;


import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenFolder extends Command {
    private String folderPath;
    public OpenFolder(String folderPath) {
        addArgument("start");
        addArgument(folderPath);
        this.folderPath = folderPath;
    }

    @Override
    public File getWorkingDirectory() {
        String classPath = getClass().getResource("/").getPath();
        Logger.getLogger(getClass().getName()).log(Level.INFO, classPath);
        return new File(classPath);
    }

    @Override
    public String toString() {
        return "start " + folderPath;
    }
}
