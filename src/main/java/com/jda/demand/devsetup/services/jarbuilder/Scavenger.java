package com.jda.demand.devsetup.services.jarbuilder;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.concurrent.Service;
import org.apache.commons.io.FileUtils;
import javafx.concurrent.Task;

public class Scavenger extends Service<Void> {
    private String jarName;

    public Scavenger() {
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }


    public Scavenger(String jarName) {
        this.jarName = jarName;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Cleaning up temp files");
                File earDir = new File("ear");
                File comDir = new File("com");
                File jarFile = new File(getJarName());
                if (jarFile.exists())
                    FileUtils.deleteQuietly(jarFile);
                if (earDir.exists())
                    FileUtils.deleteDirectory(earDir);
                if (comDir.exists())
                    FileUtils.deleteDirectory(comDir);
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "All Cleaned");
                return null;
            }
        };
    }
}
