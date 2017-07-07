package com.jda.demand.devsetup.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlsController implements Initializable {

    @FXML
    private Button installButton;

    public void onInstallButton() {
        System.out.println("Install Button Clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
