package com.aamatucci.sortvisualizerfx.sorting;

import java.util.List;

public class MergeSort extends SortAlgorithm {

    @Override
    protected void sort() throws InterruptedException {
        mergeSort(this.list, 0, list.size()-1);
    }

    private void merge(List<Integer> arr, int startL, int mid, int end) throws InterruptedException
    {
        int startR = mid + 1;

        if (arr.get(mid) <= arr.get(startR)) {
            return;
        }

        while (startL <= mid && startR <= end) {
            if (arr.get(startL) <= arr.get(startR)) {
                startL++;
                pause();
            }
            else {
                int value = arr.get(startR);
                int index = startR;

                while (index != startL) {
                    arr.set(index, arr.get(index -1));
                    callback.cursors(index);
                    index--;
                    pause();
                }
                arr.set(startL, value);
                callback.cursors(startL);
                pause();

                startL++;
                mid++;
                startR++;
            }
        }
    }

    private void mergeSort(List<Integer> arr, int left, int right) throws InterruptedException
    {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

}
