package com.aamatucci.sortvisualizerfx;

import com.aamatucci.sortvisualizerfx.sorting.Algorithm;
import com.aamatucci.sortvisualizerfx.sorting.SortAlgorithm;
import com.aamatucci.sortvisualizerfx.sorting.SortCallback;
import com.aamatucci.sortvisualizerfx.sorting.SortingAlgorithmFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class ViewModel {

    private ObservableList<Integer> list = FXCollections.observableArrayList();
    private ObservableList<Algorithm> algorithms = FXCollections.observableArrayList(Algorithm.values());
    private ObjectProperty<Algorithm> selectedAlgorithm = new SimpleObjectProperty<>();

    public ObjectProperty<SortAlgorithm> algorithmProperty() {
        return algorithm;
    }

    private ObjectProperty<SortAlgorithm> algorithm = new SimpleObjectProperty<>();


    public ViewModel(){
        selectedAlgorithm.addListener((observable, oldValue, a) -> {
            algorithm.set(SortingAlgorithmFactory.getAlgorithm(a));
            resetList();
        });
    }

    public void resetList(){
        list.clear();
        for (int i = 0; i<Constants.ELEMENTS_COUNT; i++){
            list.add(new Random().nextInt(Constants.MAX));
        }
    }

    public ObservableList<Integer> getList() {
        return list;
    }

    public void startSort(SortCallback sortCallback){
        algorithm.get().sort(list, sortCallback);
    }

    public void cancelSort(){
        algorithm.get().cancel();
    }

    public ObservableList<Algorithm> getAlgorithms() {
        return algorithms;
    }

    public Algorithm getSelectedAlgorithm() {
        return selectedAlgorithm.get();
    }

    public ObjectProperty<Algorithm> selectedAlgorithmProperty() {
        return selectedAlgorithm;
    }
}
