package com.belkin.javacore.counter;

import com.belkin.javacore.SiteVisitCounter;

public class UnsynchronizedCounter implements SiteVisitCounter {
    private int count = 0;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        count++;
    }

    @Override
    public int getVisitCount() {
        return count;
    }

}
