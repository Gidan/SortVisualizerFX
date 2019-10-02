package com.aamatucci.sortvisualizerfx.sorting;

import java.util.List;

public abstract class SortAlgorithm {

    protected List<Integer> list;
    protected SortCallback callback;

    public void sort(List<Integer> list, SortCallback callback) {
        this.list = list;
        this.callback = callback;
        this.callback.onStart();
        Thread sortThread = new Thread(() -> {
            sort();
            this.callback.onFinished();
        });
        sortThread.start();
    }

    protected void sleep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract void sort();

}
