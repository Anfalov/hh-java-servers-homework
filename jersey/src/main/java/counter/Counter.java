package counter;

public class Counter {

    private static Integer counter = 0;

    public static Integer getCounter() {
        return counter;
    }

    public static void increaseCounter() {
        counter++;
    }

    public static void decreaseCounterByValue(Integer value) {
        counter -= value;
    }

    public static void clearCounter() {
        counter = 0;
    }
}
