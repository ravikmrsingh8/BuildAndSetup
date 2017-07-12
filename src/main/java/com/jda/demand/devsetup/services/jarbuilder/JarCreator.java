package com.jda.demand.devsetup.services.jarbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.utils.Constants;
import com.jda.demand.devsetup.utils.Utility;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class JarCreator extends Service<Void> {
    private String jarName;

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public JarCreator() {

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                String rootPackage = (String)Lookup.getInstance().getVariables().get(Constants.ROOT_PACKAGE);
                if(rootPackage == null || rootPackage.isEmpty()) return null;

                String javaHome  = Lookup.getInstance().getEnvironmentVariables().get(Constants.ENV_JAVA_HOME);
                if(javaHome == null) {
                    javaHome = System.getenv(Constants.ENV_JAVA_HOME);
                }
                if (javaHome == null) {
                    throw new RuntimeException("JAVA_HOME Not found");
                }
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Running Jar exe ");

                String exe = "\"" + javaHome + "\\bin\\jar\"";
                ProcessBuilder builder = new ProcessBuilder(exe, "cvf", getJarName(),"-C",Constants.OUTPUT_FOLDER, rootPackage);
                builder.redirectErrorStream(true);
                StringBuilder result = new StringBuilder();

                Process p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;
                while ((line = r.readLine()) != null) {
                    result.append(line + "\n");
                }
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, result.toString());
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Create Jar done  ");
                return null;
            }
        };
    }
}
