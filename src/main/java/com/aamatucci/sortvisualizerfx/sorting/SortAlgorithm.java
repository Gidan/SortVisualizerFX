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
        this.run.set(true);
        this.sort();
    }

    protected void pause() throws InterruptedException {
        Thread.sleep(Constants.SLEEP);
    }

    protected void exchange(int i, int j) {
        int temp = this.list.get(i);
        this.list.set(i, this.list.get(j));
        this.list.set(j, temp);
    }

    protected boolean shouldRun(){
        return this.run.get();
    }

    public void cancel() {
        this.run.set(false);
    }

    protected abstract void sort() throws InterruptedException;

}
