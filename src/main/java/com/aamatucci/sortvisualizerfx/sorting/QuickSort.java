package com.aamatucci.sortvisualizerfx.sorting;

class QuickSort extends SortAlgorithm {

    @Override
    protected void sort() throws InterruptedException {
        quicksort(0, list.size() - 1);
    }

    private void quicksort(int low, int high) throws InterruptedException {
        int i = low, j = high;
        int pivot = list.get(low + (high-low) / 2);

        while (i <= j && shouldRun()) {
            while (list.get(i) < pivot && shouldRun()) {
                i++;
                this.callback.cursors(i, j);
                pause();
            }

            while (list.get(j) > pivot && shouldRun()) {
                j--;
                this.callback.cursors(i, j);
                pause();
            }

            if (i <= j && shouldRun()) {
                exchange(i, j);
                pause();
                i++;
                j--;
            }
        }

        if (low < j && shouldRun())
        {
            quicksort(low, j);
        }

        if (i < high && shouldRun())
        {
            quicksort(i, high);
        }
    }

}
