package com.jda.demand.devsetup;

import com.jda.demand.devsetup.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/bootstrap3.fxml"));
        Parent root = loader.load();
        Stage window = primaryStage;
        Scene scene = new Scene(root, 860, 620);
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/setup.png"));
        window.getIcons().add(image);
        window.setTitle("Dev Environment Setup");
        window.setScene(scene);
        window.setResizable(false);
        window.sizeToScene();

        MainController controller = loader.getController();
        controller.setWindow(window);
        controller.setScene(scene);

        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

