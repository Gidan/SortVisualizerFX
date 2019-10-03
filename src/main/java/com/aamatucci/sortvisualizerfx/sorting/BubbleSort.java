package com.aamatucci.sortvisualizerfx.sorting;

class BubbleSort extends SortAlgorithm {

    @Override
    protected void sort() throws InterruptedException {
        for (int i = list.size() - 1; i > 1 && shouldRun(); i--) {
            this.callback.cursors(i);
            pause();
            for (int j = 0; j < i && shouldRun(); j++) {
                this.callback.cursors(i,j);
                pause();
                if (list.get(j) > list.get(j + 1)) {
                    this.callback.cursors(j,j+1);
                    exchange(j, j + 1);
                }

            }
        }
    }
}
