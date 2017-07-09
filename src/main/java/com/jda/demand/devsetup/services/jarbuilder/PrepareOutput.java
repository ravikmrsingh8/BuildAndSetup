package com.jda.demand.devsetup.services.jarbuilder;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jda.demand.devsetup.utils.Constants;
import javafx.concurrent.Service;
import org.apache.commons.io.FileUtils;

import javafx.concurrent.Task;

public class PrepareOutput extends Service<Void> {
    private String jarName;

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public PrepareOutput() {

    }

    private void openFolder(File dir) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(dir);
        }
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Preparing Output folder ");
                File outputDir = new File(Constants.OUTPUT_FOLDER);
                if (outputDir.exists()) {
                    FileUtils.cleanDirectory(outputDir);
                }
                File jarFile = new File(getJarName());
                File earDir = new File("ear");
                if (outputDir.exists() || outputDir.mkdir()) {
                    if (earDir.exists())
                        FileUtils.copyDirectory(earDir, new File(Constants.OUTPUT_FOLDER + "ear/"));
                    if (jarFile.exists())
                        FileUtils.copyFileToDirectory(jarFile, new File(Constants.OUTPUT_FOLDER));
                    openFolder(new File(Constants.OUTPUT_FOLDER));
                } else {
                    throw new RuntimeException("Output folder not created");
                }
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Preparing Output done ");
                return null;
            }
        };
    }
}
