package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.Main;
import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.lookup.Preferences;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    public AnchorPane getRootPane() {
        return rootPane;
    }


    private Lookup lookup;
    private Logger logger;

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void initialize(URL location, ResourceBundle resources) {
        if (!Main.splashLoaded) {
            splash();
            setLookup(Lookup.getInstance());
            setLogger(Logger.getLogger(getClass().getName()));

            Preferences preferences = Preferences.getInstance();
            if (preferences.isExist()) {
                getLogger().log(Level.INFO, "Preferences found");
                preferences.load();
                preferences.getPropertiesNames().forEach((key) -> {
                    getLookup().getVariables().put(key, preferences.getProperty(key));
                    getLogger().log(Level.INFO, String.format("{%s : %s}", key, preferences.getProperty(key)));
                });
            }
        }
    }

    private void splash() {
        try {
            Main.splashLoaded = true;
            StackPane splash = FXMLLoader.load(getClass().getResource("/splash.fxml"));
            getRootPane().getChildren().setAll(splash);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(1500), splash);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(1);
            fadeOut.setCycleCount(1);
            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/bootstrap3.fxml"));
                    getRootPane().getChildren().setAll(pane);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            fadeOut.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}