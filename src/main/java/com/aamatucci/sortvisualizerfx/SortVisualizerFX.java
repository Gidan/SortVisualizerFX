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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();

        stage.setScene(new Scene(root, Constants.DEFAULT_WINDOW_WIDTH, Constants.DEFAULT_WINDOW_HEIGHT));
        stage.setMinHeight(Constants.DEFAULT_WINDOW_HEIGHT);
        stage.setMinWidth(Constants.DEFAULT_WINDOW_WIDTH);
        stage.setTitle(SortVisualizerFX.class.getSimpleName());
        stage.show();

        stage.setOnCloseRequest(event -> controller.close());
    }
}
