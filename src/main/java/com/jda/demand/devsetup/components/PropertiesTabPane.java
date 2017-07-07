package com.jda.demand.devsetup.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class PropertiesTabPane extends Parent {

    public PropertiesTabPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/propertiesTabPane.fxml"));
        try {
            getChildren().add(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
