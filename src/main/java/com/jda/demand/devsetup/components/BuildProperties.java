package com.jda.demand.devsetup.components;

import com.jda.demand.devsetup.lookup.Lookup;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BuildProperties {
    public static void displayProperties()  {
        Stage window = new Stage();
        ScrollPane pane = new ScrollPane();
        pane.setPadding(new Insets(10));
        VBox vBox = new VBox(5);
        vBox.setPrefWidth(570);
        vBox.setStyle("-fx-background-color:WHITE");
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(getAllBuildProps());
        pane.setContent(vBox);
        Scene scene = new Scene(pane, 600, 450);
        Image image = new Image(BuildProperties.class.getResourceAsStream("/images/setup.png"));
        window.getIcons().add(image);
        window.setTitle("Build Properties");
        window.setScene(scene);
        window.setResizable(false);
        window.sizeToScene();
        window.showAndWait();
    }

    private static List<Node> getAllBuildProps() {
        List<Node> labels = new ArrayList<>();
        Lookup.getInstance().getBuildProperties().forEach((key, value) -> labels.add(new Label(key + ": " + value)));
        return labels;
    }
}
