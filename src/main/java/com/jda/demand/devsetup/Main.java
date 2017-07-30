package com.jda.demand.devsetup;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/bootstrap3.fxml"));
        Parent root = loader.load();
        Stage window = primaryStage;
        window.setOnCloseRequest(e -> savePreferences());
        Scene scene = new Scene(root, 860, 650);
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/setup.png"));
        window.getIcons().add(image);
        window.setTitle("Dev Environment Setup");
        window.setScene(scene);
        window.setResizable(false);
        window.sizeToScene();
        window.show();

        Lookup.getInstance().getVariables().put(Constants.MAIN_APP, this);

    }
    public void savePreferences() {
        Preferences preferences = Preferences.getInstance();
        Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Lookup Variables %s", Lookup.getInstance().getVariables()));
        Lookup.getInstance().getVariables().forEach((key, value) -> {
            if (Constants.CIS_HOME.equals(key)) preferences.setProperty(Constants.CIS_HOME, (String) value);
            if (Constants.ENV_FILE.equals(key)) preferences.setProperty(Constants.ENV_FILE, (String) value);
        });
        preferences.save();
    }

    public static boolean splashLoaded = false;
    public static void main(String[] args) {
        launch(args);
    }

}

