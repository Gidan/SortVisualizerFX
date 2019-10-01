package com.aamatucci.sortvisualizerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SortVisualizerFX extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("SortVisualizerFX");
        stage.show();
    }
}
