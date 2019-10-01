package com.aamatucci.sortvisualizerfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

public class MainController {

    @FXML
    public HBox listContainer;

    private ViewModel viewModel;

    @FXML
    private void initialize() {
        viewModel = new ViewModel();

        viewModel.getList().addListener((ListChangeListener<Integer>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    List<? extends Integer> addedSubList = change.getAddedSubList();
                    addedSubList.forEach(i -> {
                        Rectangle r = new Rectangle(10, i, Color.BLUE);
                        listContainer.getChildren().add(r);
                    });
                } else if (change.wasReplaced()) {
                    System.out.println("rep from " + change.getFrom());
                    ((Rectangle)listContainer.getChildren().get(change.getFrom())).setHeight(viewModel.getList().get(change.getFrom()));
                }
            }
        });

        viewModel.resetList();
    }

    private void resetList() {

    }

}
