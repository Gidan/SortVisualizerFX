module SortVisualizerFX {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    exports com.aamatucci.sortvisualizerfx;
    exports com.aamatucci.sortvisualizerfx.sorting;
    opens com.aamatucci.sortvisualizerfx to javafx.fxml;
}