package com.aamatucci.sortvisualizerfx.sorting;

public class SortingAlgorithmFactory {

    public static SortAlgorithm getAlgorithm(Algorithm algorithm){
        SortAlgorithm sortAlgorithm = null;
        switch (algorithm){
            case QUICKSORT:
                sortAlgorithm = new QuickSort();
                break;
        }

        return sortAlgorithm;
    }


}
