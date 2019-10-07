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

    @FXML
    public Button btnStop;

    private ViewModel viewModel;

    @FXML
    private void initialize() {
        this.viewModel = new ViewModel();

        this.viewModel.getList().addListener((ListChangeListener<Integer>) change -> {
            while (change.next()) {
                if (change.wasReplaced()) {
                    Platform.runLater(() -> ((Rectangle) this.listContainer.getChildren().get(change.getFrom())).setHeight(this.viewModel.getList().get(change.getFrom())));
                } else if (change.wasAdded()) {
                    List<? extends Integer> addedSubList = change.getAddedSubList();
                    addedSubList.forEach(i -> {
                        Rectangle r = new Rectangle(10, i, Color.BLUE);
                        this.listContainer.getChildren().add(r);
                    });
                } else if (change.wasRemoved()) {
                    this.listContainer.getChildren().remove(change.getFrom(), change.getRemovedSize());
                }
            }
        });

        this.btnSort.disableProperty().bind(this.viewModel.sortRunningProperty());
        this.btnStop.disableProperty().bind(this.viewModel.sortRunningProperty().not());

        this.cmbAlgorithm.setItems(this.viewModel.getAlgorithms());
        this.cmbAlgorithm.getSelectionModel().select(0);
        this.viewModel.selectedAlgorithmProperty().bind(this.cmbAlgorithm.valueProperty());

        this.btnSort.setOnMouseClicked(event ->
                this.viewModel.startSort(new SortCallback() {
                    @Override
                    public void onStart() {
                        Platform.runLater(() -> MainController.this.lblStatus.setText("sorting..."));
                    }

                    @Override
                    public void onInterrupted() {
                        Platform.runLater(() -> MainController.this.lblStatus.setText("interrupted"));
                    }

                    @Override
                    public void onFinished() {
                        MainController.this.listContainer.getChildren().stream().map(node -> (Rectangle) node).forEach(rectangle -> rectangle.setFill(Color.BLUE));
                        Platform.runLater(() -> MainController.this.lblStatus.setText("done"));
                    }

                    @Override
                    public void cursors(int... cursors) {
                        Platform.runLater(() -> MainController.this.listContainer.getChildren().stream().map(node -> (Rectangle) node).forEach(rectangle -> {
                            boolean highlighted = Arrays.stream(cursors).anyMatch(c -> c == MainController.this.listContainer.getChildren().indexOf(rectangle));
                            rectangle.setFill(highlighted ? Color.RED : Color.BLUE);
                        }));

                    }
                }));

        this.btnStop.setOnMouseClicked(event -> this.viewModel.cancelSort());

    }

    void close() {
        this.viewModel.cancelSort();
    }

}
