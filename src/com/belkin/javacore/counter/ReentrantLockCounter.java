package com.belkin.javacore.counter;

import com.belkin.javacore.SiteVisitCounter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void incrementVisitCount() {
        lock.lock();
        try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            count++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}


