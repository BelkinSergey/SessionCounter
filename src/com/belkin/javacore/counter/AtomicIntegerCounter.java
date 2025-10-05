package com.belkin.javacore.counter;

import com.belkin.javacore.SiteVisitCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        count.incrementAndGet();
    }

    @Override
    public int getVisitCount() {
        return count.get();
    }
}
