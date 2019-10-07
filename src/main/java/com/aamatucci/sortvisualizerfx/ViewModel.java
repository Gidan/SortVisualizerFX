package com.aamatucci.sortvisualizerfx;

import com.aamatucci.sortvisualizerfx.sorting.Algorithm;
import com.aamatucci.sortvisualizerfx.sorting.SortAlgorithm;
import com.aamatucci.sortvisualizerfx.sorting.SortCallback;
import com.aamatucci.sortvisualizerfx.sorting.SortingAlgorithmFactory;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class ViewModel {

    private ObservableList<Integer> list = FXCollections.observableArrayList();
    private ObservableList<Algorithm> algorithms = FXCollections.observableArrayList(Algorithm.values());
    private ObjectProperty<Algorithm> selectedAlgorithm = new SimpleObjectProperty<>();
    private ObjectProperty<SortAlgorithm> algorithm = new SimpleObjectProperty<>();
    private BooleanProperty sortRunning = new SimpleBooleanProperty(false);

    private Thread sortThread;
    private SortCallback sortCallback;

    public ViewModel(){
        this.selectedAlgorithm.addListener((observable, oldValue, a) -> {
            this.algorithm.set(SortingAlgorithmFactory.getAlgorithm(a));
            this.resetList();
        });
    }

    private void resetList(){
        this.list.clear();
        for (int i = 0; i<Constants.ELEMENTS_COUNT; i++){
            this.list.add(new Random().nextInt(Constants.MAX));
        }
    }

    ObservableList<Integer> getList() {
        return this.list;
    }

    void startSort(SortCallback sortCallback){
        this.sortCallback = sortCallback;
        this.sortThread = new Thread(() -> {
            try {
                this.sortCallback.onStart();
                this.algorithm.get().sort(this.list, sortCallback);
                this.sortCallback.onFinished();
            } catch (InterruptedException e) {
                this.sortCallback.onInterrupted();
            }
            finally {
                Platform.runLater(()-> this.sortRunning.setValue(false));
            }
        });
        this.sortRunning.setValue(true);
        this.sortThread.start();
    }

    void cancelSort(){
        this.algorithm.get().cancel();
    }

    ObservableList<Algorithm> getAlgorithms() {
        return this.algorithms;
    }

    ObjectProperty<Algorithm> selectedAlgorithmProperty() {
        return this.selectedAlgorithm;
    }

    BooleanProperty sortRunningProperty() {
        return this.sortRunning;
    }
}
