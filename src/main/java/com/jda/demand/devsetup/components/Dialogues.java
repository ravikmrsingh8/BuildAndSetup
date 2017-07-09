package com.jda.demand.devsetup.components;

import java.util.function.Consumer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Dialogues {
    public static void showErrorDialogue(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(errorMessage);
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType rs) {
                if (rs == ButtonType.OK) {
                    // Do cleanup
                    //clear();
                }
            }
        });
    }

    public static void showInformationDialogue(String information) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(information);
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType rs) {
                if (rs == ButtonType.OK) {
                    //clear();
                }
            }

        });
    }
}
