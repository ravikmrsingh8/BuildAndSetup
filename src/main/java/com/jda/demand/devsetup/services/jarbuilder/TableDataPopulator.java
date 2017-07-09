package com.jda.demand.devsetup.services.jarbuilder;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jda.demand.devsetup.utils.JarUtility;
import com.jda.demand.devsetup.utils.XFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TableDataPopulator extends Service<ObservableList<XFile>> {
    private List<File> files;

    public TableDataPopulator(List<File> files) {
        this.files = files;
    }

    private List<File> getFiles() {
        return files;
    }

    @Override
    protected Task<ObservableList<XFile>> createTask() {
        return new Task<ObservableList<XFile>>() {
            @Override
            protected ObservableList<XFile> call() throws Exception {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Adding selected files to Table");
                ObservableList<XFile> obFiles = FXCollections.observableArrayList();
                for (File file : getFiles()) {
                    XFile xFile = new XFile();
                    xFile.setAbsolutePath(file.getAbsolutePath());

                    if (file.getAbsolutePath().endsWith(".class")) {
                        xFile.setPath(JarUtility.getPathWithPackage(file));
                    } else {
                        xFile.setPath(JarUtility.getPathAfterScpo(file));
                    }
                    System.out.println(xFile.getPath());
                    obFiles.add(xFile);
                }
                return obFiles;
            }
        };
    }
}
