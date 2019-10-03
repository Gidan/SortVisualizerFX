package com.aamatucci.sortvisualizerfx.sorting;

import com.aamatucci.sortvisualizerfx.Constants;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SortAlgorithm {

    protected List<Integer> list;
    protected SortCallback callback;

    private AtomicBoolean run = new AtomicBoolean(false);

    public void sort(List<Integer> list, SortCallback callback) throws InterruptedException {
        this.list = list;
        this.callback = callback;
        run.set(true);
        sort();
    }

    protected void pause() throws InterruptedException {
        Thread.sleep(Constants.SLEEP);
    }

    protected void exchange(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    protected boolean shouldRun(){
        return run.get();
    }

    public void cancel() {
        run.set(false);
    }

    protected abstract void sort() throws InterruptedException;

}
