package com.belkin.javacore.counter;

import com.belkin.javacore.SiteVisitCounter;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private int count = 0;
    private final Object lock = new Object();

    public void incrementVisitCount() {
        synchronized (lock) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            count++;
        }
    }

    @Override
    public int getVisitCount() {
        synchronized (lock) {
            return count;
        }
    }
}




