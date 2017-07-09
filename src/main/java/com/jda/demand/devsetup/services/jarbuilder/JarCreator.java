package com.jda.demand.devsetup.services.jarbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                if (!new File("com").exists()) return null;
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Running Jar exe ");
                final String javaHome = System.getenv("JAVA_HOME");
                String exe = "\"" + javaHome + "\\bin\\jar\"";
                ProcessBuilder builder = new ProcessBuilder(exe, "cvf", getJarName(), "com");
                builder.redirectErrorStream(true);
                StringBuilder result = new StringBuilder();

                Process p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;
                while ((line = r.readLine()) != null) {
                    result.append(line + "\n");
                }
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Create Jar done  ");
                return null;
            }
        };
    }
}
