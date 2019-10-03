package com.aamatucci.sortvisualizerfx;

import com.aamatucci.sortvisualizerfx.sorting.Algorithm;
import com.aamatucci.sortvisualizerfx.sorting.SortCallback;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;

public class MainController {

    @FXML
    public HBox listContainer;

    @FXML
    public ComboBox<Algorithm> cmbAlgorithm;

    @FXML
    public Button btnSort;

    @FXML
    public Label lblStatus;

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
                } else if (change.wasRemoved()){
                    listContainer.getChildren().remove(change.getFrom(), change.getRemovedSize());
                }
            }
        });

        btnSort.disableProperty().bind(viewModel.algorithmProperty().isNull());
        cmbAlgorithm.setItems(viewModel.getAlgorithms());
        cmbAlgorithm.getSelectionModel().select(0);
        viewModel.selectedAlgorithmProperty().bind(cmbAlgorithm.valueProperty());

        btnSort.setOnMouseClicked(event -> {
            viewModel.startSort(new SortCallback() {
                @Override
                public void onStart() {
                    lblStatus.setText("sorting...");
                }

                @Override
                public void onInterrupted() {
                    Platform.runLater(() -> lblStatus.setText("interrupted"));
                }

                @Override
                public void onFinished() {
                    listContainer.getChildren().stream().map(node -> (Rectangle)node).forEach(rectangle -> {
                        rectangle.setFill(Color.BLUE);
                    });
                    Platform.runLater(() -> lblStatus.setText("done"));
                }

                @Override
                public void cursors(int... cursors) {
                    Platform.runLater(() -> listContainer.getChildren().stream().map(node -> (Rectangle)node).forEach(rectangle -> {
                        boolean highlighted = Arrays.stream(cursors).anyMatch(c -> c == listContainer.getChildren().indexOf(rectangle));
                        rectangle.setFill(highlighted ? Color.RED : Color.BLUE);
                    }));

                }
            });
        });

    }

    public void close(){
        viewModel.cancelSort();
    }

}
