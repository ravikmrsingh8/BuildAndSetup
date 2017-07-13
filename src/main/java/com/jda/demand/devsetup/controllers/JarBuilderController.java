package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.services.jarbuilder.*;
import com.jda.demand.devsetup.components.Dialogues;
import com.jda.demand.devsetup.utils.Constants;
import com.jda.demand.devsetup.utils.Utility;
import com.jda.demand.devsetup.utils.XFile;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.*;

public class JarBuilderController implements Initializable {
    private String jarName;
    private DirectoryChooser dirChooser;
    private FileChooser fileChooser;

    @FXML
    private TableView<XFile> tableView;
    @FXML
    private ImageView imageView;
    @FXML
    private Label label;
    @FXML
    private TextField inputJarName;


    public JarBuilderController() {
        super();
        setDirChooser(new DirectoryChooser());
        setFileChooser(new FileChooser());
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }


    public TableView<XFile> getTableView() {
        return tableView;
    }

    public ImageView getImageView() {
        return imageView;
    }


    public Label getLabel() {
        return label;
    }

    public TextField getInputJarName() {
        return inputJarName;
    }

    public DirectoryChooser getDirChooser() {
        return dirChooser;
    }

    public void setDirChooser(DirectoryChooser dirChooser) {
        this.dirChooser = dirChooser;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public void setFileChooser(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    //Event Handlers
    public void onAddFile() {
        List<File> allFiles = getFileChooser().showOpenMultipleDialog(null);
        if (allFiles == null || allFiles.isEmpty()) return;
        /*Remember directory, it will be used next time when we open Chooser window; */
        getFileChooser().setInitialDirectory(allFiles.get(0).getParentFile());
        showProcessing();
        new Thread(() -> addInTable(allFiles)).start();
    }

    public void onAddFolderButton() {

        File dir = getDirChooser().showDialog(null);
        if (dir == null) return;
        //Remember this directory, it will be used next time when we open Chooser window;
        getDirChooser().setInitialDirectory(dir);
        showProcessing();
        new Thread(() -> {
            Iterator<File> iterator = FileUtils.iterateFiles(dir, null, true);
            List<File> files = new ArrayList<File>();
            while (iterator.hasNext()) {
                files.add(iterator.next());
            }
            addInTable(files);
        }).start();
    }

    public void onDeleteButton() {
        getTableView().getItems().removeAll(getTableView().getSelectionModel().getSelectedItems());
        getLabel().setText(getTableView().getItems().size() == 0 ? "" : getTableView().getItems().size() + " Files");
    }

    public void onCreateButton() {
        if (getTableView().getItems().isEmpty()) {
            Dialogues.showErrorDialogue("No files selected");
            return;
        }

        setJarName(Utility.getValidJarName(getInputJarName().getText()));

        PrepareOutput prepare = new PrepareOutput();
        prepare.setJarName(getJarName());
        prepare.setOnFailed((WorkerStateEvent event) -> {
            processOnError(prepare.getException().getMessage());
        });

        prepare.setOnSucceeded((WorkerStateEvent event) -> {
            closeProcessing();
            clearUI();
            Dialogues.showInformationDialogue("Process Completed!");
        });

        JarCreator creator = new JarCreator();
        creator.setJarName(getJarName());
        creator.setOnFailed((WorkerStateEvent event) -> {
            processOnError(creator.getException().getMessage());
        });
        creator.setOnSucceeded((WorkerStateEvent event) -> {
            getLabel().setText("Preparing Output");
            prepare.start();
        });

        FileCopier copier = new FileCopier();
        copier.setTableData(getTableView().getItems());
        copier.setOnFailed((WorkerStateEvent event) -> {
            processOnError(copier.getException().getMessage());
        });
        copier.setOnSucceeded((WorkerStateEvent event) -> {
            getLabel().setText("Creating Jar");
            creator.start();
        });

        Scavenger scavenger = new Scavenger();
        scavenger.setJarName(getJarName());
        scavenger.setOnFailed((WorkerStateEvent event) -> {
            closeProcessing();
            clearUI();
            Dialogues.showErrorDialogue(scavenger.getException().getMessage());
        });

        scavenger.setOnSucceeded((WorkerStateEvent event) -> {
            copier.start();
            getLabel().setText("Copying files..");
        });
        scavenger.start();
        showProcessing();
        getLabel().setText("Cleaning up ..");
    }

    private void addInTable(List<File> allFiles) {

        TableDataPopulator service = new TableDataPopulator(allFiles);
        service.setOnFailed((WorkerStateEvent event) -> {
            Dialogues.showErrorDialogue(service.getException().getMessage());
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            getTableView().getItems().addAll(service.getValue());
            closeProcessing();
            getLabel().setText(getTableView().getItems().size() + " Files");
        });
        service.start();
    }

    private void processOnError(String errorMsg) {
        new Scavenger(getJarName()).start();
        closeProcessing();
        clearUI();
        Dialogues.showErrorDialogue(errorMsg);
    }

    private void clearUI() {
        getInputJarName().clear();
        getTableView().getItems().clear();
        Lookup.getInstance().getVariables().put(Constants.ROOT_PACKAGE, "");
    }

    private void showProcessing() {
        getLabel().setText("Please Wait");
        getImageView().setImage(new Image("images/processing.gif"));

    }

    private void closeProcessing() {
        getLabel().setText(null);
        getImageView().setImage(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTableView().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        getTableView().setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
}
