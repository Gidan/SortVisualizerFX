package com.aamatucci.sortvisualizerfx.sorting;

class BubbleSort extends SortAlgorithm {

    @Override
    protected void sort() throws InterruptedException {
        for (int i = this.list.size() - 1; i > 1 && this.shouldRun(); i--) {
            this.callback.cursors(i);
            this.pause();
            for (int j = 0; j < i && this.shouldRun(); j++) {
                this.callback.cursors(i, j);
                this.pause();
                if (this.list.get(j) > this.list.get(j + 1)) {
                    this.callback.cursors(j, j + 1);
                    this.exchange(j, j + 1);
                }

            }
        }
    }
}
