import com.belkin.javacore.MultithreadingSiteVisitor;
import com.belkin.javacore.SiteVisitCounter;
import com.belkin.javacore.counter.*;

public class Main {
    public static void main(String[] args) {
        try {
            int[] threadCounts = {10, 1000};
            for (int numThreads : threadCounts) {
                System.out.println("=== Тестирование с " + numThreads + " потоками ===");
                testCounter(new UnsynchronizedCounter(), "UnsynchronizedCounter", numThreads);
                testCounter(new VolatileCounter(), "VolatileCounter", numThreads);
                testCounter(new SynchronizedBlockCounter(), "SynchronizedBlockCounter", numThreads);
                testCounter(new AtomicIntegerCounter(), "AtomicIntegerCounter", numThreads);
                testCounter(new ReentrantLockCounter(), "ReentrantLockCounter", numThreads);

                System.out.println();
            }
        } catch (InterruptedException e) {
            System.err.println("Тестирование было прервано: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public static void testCounter(SiteVisitCounter counter, String counterName, int numThreads)
            throws InterruptedException {
        MultithreadingSiteVisitor visitor = new MultithreadingSiteVisitor(counter);

        visitor.visitMultiThread(numThreads);
        visitor.waitUntilAllVisited();

        System.out.printf("%-25s: Время = %.3f сек, Счетчик = %d%n",
                counterName,
                visitor.getTotalTimeOfHandling(),
                visitor.getCurrentCount());
    }
}


