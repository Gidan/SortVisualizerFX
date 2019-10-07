package com.aamatucci.sortvisualizerfx.sorting;

import java.util.List;

public class HeapSort extends SortAlgorithm {
    @Override
    protected void sort() throws InterruptedException {
        int size = this.list.size();

        for (int i = size / 2 - 1; i >= 0; i--) {
            this.createHeap(this.list, size, i);
        }

        // One by one extract an element from heap
        for (int i = size - 1; i >= 0; i--) {
            // Move current root to end
            this.callback.cursors(0, i);
            this.exchange(0, i);
            this.pause();

            this.createHeap(this.list, i, 0);
        }
    }

    private void createHeap(List<Integer> list, int size, int root) throws InterruptedException {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        // If left child is larger than root
        if (left < size && list.get(left) > list.get(largest)) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < size && list.get(right) > list.get(largest)) {
            largest = right;
        }

        // If largest is not root
        if (largest != root) {
            this.callback.cursors(root, largest);
            this.exchange(root, largest);
            this.pause();

            this.createHeap(list, size, largest);
        }
    }
}
