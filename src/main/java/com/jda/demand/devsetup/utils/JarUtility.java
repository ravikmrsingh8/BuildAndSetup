package com.jda.demand.devsetup.utils;

import com.jda.demand.devsetup.lookup.EnvironmentVariables;
import javafx.scene.control.TextField;

import java.io.File;

public class JarUtility {

    public static final String SCPO_HOME = EnvironmentVariables.getInstance().getVariable(Constants.ENV_BUILD_ROOT);
    public static String getPathWithPackage(File javaFile) {
        String file = javaFile.getAbsolutePath();
        String _$ = File.separator;
        String classesDir = SCPO_HOME + _$ + Constants.WEBLOGIC + _$ + Constants.CONFIG + _$ + Constants.CLASSES + _$;
        System.out.println(classesDir);
        System.out.println(file.replace(classesDir, ""));
        return file.replace(classesDir, "");
    }

    public static String getPathAfterScpo(File file) {
        String filePath = file.getAbsolutePath();
        String _$ = File.separator;
        return filePath.replace(SCPO_HOME + _$, "");
    }

    public static String getValidJarName(String input) {
        String jarName = Constants.DEFAULT_JAR_NAME;
        if ((input != null) && (!input.trim().isEmpty())) jarName = input;
        if (!jarName.endsWith(".jar")) jarName += ".jar";
        return jarName;
    }
}
