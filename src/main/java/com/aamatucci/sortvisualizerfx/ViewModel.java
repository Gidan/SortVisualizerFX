package com.aamatucci.sortvisualizerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class ViewModel {

    private ObservableList<Integer> list = FXCollections.observableArrayList();

    public void resetList(){
        list.clear();
        for (int i = 0; i<Constants.ELEMENTS_COUNT; i++){
            list.add(new Random().nextInt(Constants.MAX));
        }
    }

    public ObservableList<Integer> getList() {
        return list;
    }
}
