package com.aamatucci.sortvisualizerfx.sorting;

class QuickSort extends SortAlgorithm {

    @Override
    protected void sort() {
        quicksort(0, list.size() - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        int pivot = list.get(low + (high-low) / 2);

        while (i <= j) {
            while (list.get(i) < pivot) {
                i++;
                this.callback.cursors(i, j);
                sleep();
            }

            while (list.get(j) > pivot) {
                j--;
                this.callback.cursors(i, j);
                sleep();
            }

            if (i <= j) {
                exchange(i, j);
                sleep();
                i++;
                j--;
            }
        }

        if (low < j)
        {
            quicksort(low, j);
        }

        if (i < high)
        {
            quicksort(i, high);
        }
    }

    private void exchange(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }




}
