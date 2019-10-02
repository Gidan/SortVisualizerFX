package com.aamatucci.sortvisualizerfx.sorting;

public interface SortCallback {
    void onStart();
    void onFinished();
    void cursors(int... cursors);
}
