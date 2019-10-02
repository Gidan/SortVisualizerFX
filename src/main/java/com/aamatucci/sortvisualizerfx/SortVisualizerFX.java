package com.aamatucci.sortvisualizerfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SortVisualizerFX extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();

        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("SortVisualizerFX");
        stage.show();


        stage.setOnCloseRequest(event -> controller.close());





    }
}
