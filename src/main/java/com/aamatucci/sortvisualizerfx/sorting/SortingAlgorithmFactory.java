package com.aamatucci.sortvisualizerfx.sorting;

public class SortingAlgorithmFactory {

    public static SortAlgorithm getAlgorithm(Algorithm algorithm){
        SortAlgorithm sortAlgorithm = null;
        switch (algorithm){
            case quickSort:
                sortAlgorithm = new QuickSort();
                break;
            case bubbleSort:
                sortAlgorithm = new BubbleSort();
                break;
            case mergeSort:
                sortAlgorithm = new MergeSort();
                break;
            case heapSort:
                sortAlgorithm = new HeapSort();
                break;
        }

        return sortAlgorithm;
    }


}
