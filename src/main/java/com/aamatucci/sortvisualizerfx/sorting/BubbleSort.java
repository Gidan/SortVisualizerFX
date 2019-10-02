package com.aamatucci.sortvisualizerfx.sorting;

class BubbleSort extends SortAlgorithm {

    @Override
    protected void sort() {
        for (int i = list.size() - 1; i > 1 && run; i--) {
            this.callback.cursors(i);
            sleep();
            for (int j = 0; j < i && run; j++) {
                this.callback.cursors(i,j);
                sleep();
                if (list.get(j) > list.get(j + 1)) {
                    this.callback.cursors(j,j+1);
                    exchange(j, j + 1);
                }

            }
        }
    }
}
