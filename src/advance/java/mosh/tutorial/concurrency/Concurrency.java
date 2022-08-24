package advance.java.mosh.tutorial.concurrency;

// Confinement

import java.util.ArrayList;
import java.util.List;

class SimpleThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 1000; i++) {
            Concurrency.increase();
        }
    }
}
public class Concurrency {
    private static int resource = 0;
    public static int getResource() {
        return resource;
    }

    public static void increase() {
        resource++;
    }

    public void show() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new SimpleThread());
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(getResource());
    }
}