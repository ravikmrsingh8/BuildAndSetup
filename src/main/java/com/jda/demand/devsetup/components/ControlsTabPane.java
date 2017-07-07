package com.jda.demand.devsetup.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ControlsTabPane extends Parent {
    public ControlsTabPane() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controlsTab.fxml"));
        try {
            getChildren().add(loader.load());
        } catch (IOException ioe){
            throw new RuntimeException(ioe);
        }
    }
}
