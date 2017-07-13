package com.jda.demand.devsetup.components;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class JarBuilderTabPane extends Parent {
    public JarBuilderTabPane() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarBuilderTab.fxml"));
        try {
            getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
