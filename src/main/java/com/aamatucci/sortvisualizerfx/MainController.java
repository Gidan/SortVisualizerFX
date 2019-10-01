package com.aamatucci.sortvisualizerfx;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainController {

    @FXML
    public HBox listContainer;

    private ViewModel viewModel;

    @FXML
    private void initialize(){
        viewModel = new ViewModel();
    }

    private void resetList(){

    }

}
