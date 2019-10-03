package com.aamatucci.sortvisualizerfx.sorting;

import com.aamatucci.sortvisualizerfx.Constants;

import java.util.List;

public abstract class SortAlgorithm {

    protected List<Integer> list;
    protected SortCallback callback;
    private Thread sortThread;
    protected boolean run = true;

    public void sort(List<Integer> list, SortCallback callback) {
        this.list = list;
        this.callback = callback;
        run = true;
        this.callback.onStart();
        sortThread = new Thread(() -> {
            try {
                sort();
            } catch (InterruptedException e){

            }
            this.callback.onFinished();
        });
        sortThread.start();
    }

    protected void sleep() throws InterruptedException {
        Thread.sleep(Constants.SLEEP);
//        try {
//            Thread.sleep(Constants.SLEEP);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    protected void exchange(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public void cancel() {
        run = false;
        if (sortThread != null) {
            sortThread.interrupt();
        }
    }

    protected abstract void sort() throws InterruptedException;

}
