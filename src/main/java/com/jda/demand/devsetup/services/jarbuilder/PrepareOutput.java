package com.jda.demand.devsetup.services.jarbuilder;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jda.demand.devsetup.lookup.Lookup;
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

                /*if jar file exist copy to Output folder and delete jar file*/
                File jarFile = new File(getJarName());
                if (jarFile.exists()) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Copying jar in Output folder ");
                    FileUtils.copyFileToDirectory(jarFile, new File(Constants.OUTPUT_FOLDER));
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Deleting jar from Current folder ");
                    FileUtils.deleteQuietly(jarFile);
                }
                /* delete copied class files in Output Folder */
                String rootPackage = (String)Lookup.getInstance().getVariables().get(Constants.ROOT_PACKAGE);
                if(rootPackage != null && !rootPackage.isEmpty()) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Deleting class files from  Output folder ");
                    rootPackage = Constants.OUTPUT_FOLDER + File.separator + rootPackage;
                    FileUtils.deleteQuietly(new File(rootPackage));
                }

                openFolder(new File(Constants.OUTPUT_FOLDER));
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Preparing Output done ");
                return null;
            }
        };
    }
}
