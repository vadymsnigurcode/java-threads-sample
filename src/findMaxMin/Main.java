package findMaxMin;

import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Instant start = Instant.now();
        long startTime1 = System.nanoTime();
        Thread threadSearchMin = new Thread(new MinSearcher(new int[]{1, 2, 3, 4, 5, 7, 8, 9, 0}));
        Thread threadSearchMax = new Thread(new MaxSearcher(new int[]{1, 2, 3, 4, 5, 7, 8, 9, 0}));
        threadSearchMin.start();
        threadSearchMax.start();
        threadSearchMin.join();
        threadSearchMax.join();
        long stopTime = System.nanoTime();
        Instant end = Instant.now();
        System.out.println(stopTime - startTime);
        System.out.println(Duration.between(start, end));
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(System.currentTimeMillis() - startTime1);
    }
}

class MaxSearcher implements Runnable {

    int[] inputItems;
    int result;

    public MaxSearcher(int[] inputItems) {
        this.inputItems = inputItems;
    }


    @Override
    public void run() {
        int max = inputItems[0];
        for (int item : inputItems) {
            if (item > max) max = item;
        }
        result = max;
        System.out.println("max is " + max);
    }
}


class MinSearcher implements Runnable {

    int[] inputItems;
    int result;

    public MinSearcher(int[] inputItems) {
        this.inputItems = inputItems;
    }


    @Override
    public void run() {
        int min = inputItems[0];
        for (int item : inputItems) {
            if (item < min) min = item;
        }
        result = min;
        System.out.println("min is " + min);
    }
}
