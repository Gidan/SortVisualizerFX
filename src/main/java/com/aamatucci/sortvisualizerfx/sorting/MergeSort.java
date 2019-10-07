package com.aamatucci.sortvisualizerfx.sorting;

import java.util.List;

public class MergeSort extends SortAlgorithm {

    @Override
    protected void sort() throws InterruptedException {
        this.mergeSort(this.list, 0, this.list.size() - 1);
    }

    private void merge(List<Integer> arr, int startL, int mid, int end) throws InterruptedException {
        int startR = mid + 1;

        if (arr.get(mid) <= arr.get(startR)) {
            return;
        }

        while (startL <= mid && startR <= end) {
            if (arr.get(startL) <= arr.get(startR)) {
                startL++;
                this.pause();
            } else {
                int value = arr.get(startR);
                int index = startR;

                while (index != startL) {
                    arr.set(index, arr.get(index - 1));
                    this.callback.cursors(index);
                    index--;
                    this.pause();
                }
                arr.set(startL, value);
                this.callback.cursors(startL);
                this.pause();

                startL++;
                mid++;
                startR++;
            }
        }
    }

    private void mergeSort(List<Integer> arr, int left, int right) throws InterruptedException {
        if (left < right) {
            int mid = left + (right - left) / 2;

            this.mergeSort(arr, left, mid);
            this.mergeSort(arr, mid + 1, right);

            this.merge(arr, left, mid, right);
        }
    }

}
