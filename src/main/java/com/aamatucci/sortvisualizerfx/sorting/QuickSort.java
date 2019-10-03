package com.aamatucci.sortvisualizerfx.sorting;

class QuickSort extends SortAlgorithm {

    @Override
    protected void sort() throws InterruptedException {
        quicksort(0, list.size() - 1);
    }

    private void quicksort(int low, int high) throws InterruptedException {
        int i = low, j = high;
        int pivot = list.get(low + (high-low) / 2);

        while (i <= j && run) {
            while (list.get(i) < pivot && run) {
                i++;
                this.callback.cursors(i, j);
                sleep();
            }

            while (list.get(j) > pivot && run) {
                j--;
                this.callback.cursors(i, j);
                sleep();
            }

            if (i <= j && run) {
                exchange(i, j);
                sleep();
                i++;
                j--;
            }
        }

        if (low < j && run)
        {
            quicksort(low, j);
        }

        if (i < high && run)
        {
            quicksort(i, high);
        }
    }






}
