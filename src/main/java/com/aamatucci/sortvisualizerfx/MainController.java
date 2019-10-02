package com.aamatucci.sortvisualizerfx;

import com.aamatucci.sortvisualizerfx.sorting.SortCallback;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
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
                if (change.wasReplaced()) {
                   Platform.runLater(() -> ((Rectangle) listContainer.getChildren().get(change.getFrom())).setHeight(viewModel.getList().get(change.getFrom())));
                } else if (change.wasAdded()) {
                    List<? extends Integer> addedSubList = change.getAddedSubList();
                    addedSubList.forEach(i -> {
                        Rectangle r = new Rectangle(10, i, Color.BLUE);
                        listContainer.getChildren().add(r);
                    });
                }
            }
        });

        viewModel.resetList();
        viewModel.startSort(new SortCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onFinished() {
                listContainer.getChildren().stream().map(node -> (Rectangle)node).forEach(rectangle -> {
                    rectangle.setFill(Color.BLUE);
                });
            }

            @Override
            public void cursors(int... cursors) {
                Platform.runLater(() -> listContainer.getChildren().stream().map(node -> (Rectangle)node).forEach(rectangle -> {
                    boolean highlighted = Arrays.stream(cursors).anyMatch(c -> c == listContainer.getChildren().indexOf(rectangle));
                    rectangle.setFill(highlighted ? Color.RED : Color.BLUE);
                }));

            }
        });
    }

    public void close(){
        viewModel.cancelSort();
    }

}
