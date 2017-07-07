package com.jda.demand.devsetup.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class JarBuilderController implements Initializable{
    @FXML
    private Button addFileButton;


    public void onAddFile() {
        System.out.println("On Add FIle");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
