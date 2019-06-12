package com.github.sandokandias;

public class Timer {

    private long startTime, endTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    public long getTime() {
        return endTime - startTime;
    }

    public void stopAndPrint(String id) {
        stop();
        System.out.println(id + " - total time = " + getTime() + " ms.");
    }
}
