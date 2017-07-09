package com.jda.demand.devsetup.utils;

import com.jda.demand.devsetup.properties.Preferences;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JarUtility {

    public static Preferences preferences = Preferences.getInstance();

    public static String getPathWithPackage(File javaFile) {
        String file = javaFile.getAbsolutePath();
        String separator = File.separator;
        String scpoDir = preferences.getProperty(Constants.SCPO_HOME);
        if (scpoDir == null || scpoDir.isEmpty()) {
            Logger.getLogger("JarUtility").log(Level.SEVERE, "SCPO HOME Required");
            throw new RuntimeException("SCPO Home Required");
        }
        String classesDir = scpoDir + separator + Constants.WEBLOGIC + separator + Constants.CONFIG + separator + Constants.CLASSES + separator;
        System.out.println(classesDir);
        System.out.println(file.replace(classesDir, ""));
        return file.replace(classesDir, "");
    }

    public static String getPathAfterScpo(File file) {
        String filePath = file.getAbsolutePath();
        String scpoDir = preferences.getProperty(Constants.SCPO_HOME);
        if (scpoDir == null || scpoDir.isEmpty()) {
            Logger.getLogger("JarUtility").log(Level.SEVERE, "SCPO HOME Required");
            throw new RuntimeException("SCPO Home Required");
        }
        scpoDir += File.separator;
        return filePath.replace(scpoDir, "");
    }

    public static String getValidJarName(TextField input) {
        String jarName = "Test.jar";
        if ((input != null) && (!input.getText().trim().isEmpty())) jarName = input.getText();
        if (!jarName.endsWith(".jar")) jarName += ".jar";
        return jarName;
    }
}
