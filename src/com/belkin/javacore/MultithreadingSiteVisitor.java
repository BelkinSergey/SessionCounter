package com.belkin.javacore;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {
    private final SiteVisitCounter counter;
    private final List<Thread> threads = new ArrayList<>();
    private long startTime;
    private long endTime;

    public MultithreadingSiteVisitor(SiteVisitCounter counter) {
        this.counter = counter;
    }

    public void visitMultiThread(int numOfThreads) {
        threads.clear();
        startTime = System.currentTimeMillis();

        for (int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(() -> {
                counter.incrementVisitCount();
            });
            threads.add(thread);
            thread.start();

        }
    }

    public void waitUntilAllVisited() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
        endTime = System.currentTimeMillis();
    }

    public double getTotalTimeOfHandling() {
        return (endTime - startTime) / 1000.0;
    }

    public int getCurrentCount() {
        return counter.getVisitCount();
    }
}
