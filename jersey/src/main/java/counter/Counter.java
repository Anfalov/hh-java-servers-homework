package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static int getCounter() {
        return counter.get();
    }

    public static int increaseAndGetCounter() {
        return counter.incrementAndGet();
    }

    public static int decreaseAndGetCounterByValue(int value) {
        return counter.addAndGet(-value);
    }

    public static int clearAndGetCounter() {
        counter.set(0);
        return counter.get();
    }
}
