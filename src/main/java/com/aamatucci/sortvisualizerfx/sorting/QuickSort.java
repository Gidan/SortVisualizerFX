package com.aamatucci.sortvisualizerfx.sorting;

class QuickSort extends SortAlgorithm {

    @Override
    protected void sort() throws InterruptedException {
        this.quicksort(0, this.list.size() - 1);
    }

    private void quicksort(int low, int high) throws InterruptedException {
        int i = low, j = high;
        int pivot = this.list.get(low + (high-low) / 2);

        while (i <= j && this.shouldRun()) {
            while (this.list.get(i) < pivot && this.shouldRun()) {
                i++;
                this.callback.cursors(i, j);
                this.pause();
            }

            while (this.list.get(j) > pivot && this.shouldRun()) {
                j--;
                this.callback.cursors(i, j);
                this.pause();
            }

            if (i <= j && this.shouldRun()) {
                this.exchange(i, j);
                this.pause();
                i++;
                j--;
            }
        }

        if (low < j && this.shouldRun())
        {
            this.quicksort(low, j);
        }

        if (i < high && this.shouldRun())
        {
            this.quicksort(i, high);
        }
    }

}
