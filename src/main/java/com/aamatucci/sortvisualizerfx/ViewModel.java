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
        selectedAlgorithm.addListener((observable, oldValue, a) -> {
            algorithm.set(SortingAlgorithmFactory.getAlgorithm(a));
            resetList();
        });
    }

    private void resetList(){
        list.clear();
        for (int i = 0; i<Constants.ELEMENTS_COUNT; i++){
            list.add(new Random().nextInt(Constants.MAX));
        }
    }

    ObservableList<Integer> getList() {
        return list;
    }

    ObjectProperty<SortAlgorithm> algorithmProperty() {
        return algorithm;
    }

    void startSort(SortCallback sortCallback){
        this.sortCallback = sortCallback;
        sortThread = new Thread(() -> {
            try {
                this.sortCallback.onStart();
                algorithm.get().sort(list, sortCallback);
                this.sortCallback.onFinished();
            } catch (InterruptedException e) {
                this.sortCallback.onInterrupted();
            }
            finally {
                Platform.runLater(()-> sortRunning.setValue(false));
            }
        });
        sortRunning.setValue(true);
        this.sortThread.start();
    }

    void cancelSort(){
       algorithm.get().cancel();
    }

    ObservableList<Algorithm> getAlgorithms() {
        return algorithms;
    }

    ObjectProperty<Algorithm> selectedAlgorithmProperty() {
        return selectedAlgorithm;
    }

    public boolean isSortRunning() {
        return sortRunning.get();
    }

    public BooleanProperty sortRunningProperty() {
        return sortRunning;
    }
}
