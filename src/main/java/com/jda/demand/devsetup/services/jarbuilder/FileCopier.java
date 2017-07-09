package com.jda.demand.devsetup.services.jarbuilder;

import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jda.demand.devsetup.utils.XFile;
import javafx.concurrent.Service;
import org.apache.commons.io.FileUtils;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class FileCopier extends Service<Void> {
    private ObservableList<XFile> tableData;
    Logger logger = Logger.getLogger(getClass().getName());

    public void setTableData(ObservableList<XFile> tableData) {
        this.tableData = tableData;
    }

    public ObservableList<XFile> getTableData() {
        return tableData;
    }

    public FileCopier() {
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                logger.log(Level.SEVERE, "Copying files ");
                getTableData().parallelStream().forEach((XFile xFile) -> {
                    try {
                        FileUtils.copyFile(new File(xFile.getAbsolutePath()), new File(xFile.getPath()));
                        logger.log(Level.SEVERE, "Copied " + xFile.getPath());
                    } catch (IOException e) {
                        throw new RuntimeException("Copy failed");
                    }
                });
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Copying complete");
                return null;
            }
        };
    }
}
