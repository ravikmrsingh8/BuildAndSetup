package com.jda.demand.devsetup.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class BuildPropertiesController implements Initializable{

    @FXML
    private Button buildButton;

    public void onBuildButton() {
        System.out.println("Build Button Clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
