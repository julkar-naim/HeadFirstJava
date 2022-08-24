package advance.java.mosh.tutorial.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CustomThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Locking.increase();
        }
    }
}

public class Locking {
    private static int sharedResource = 0;
    private final static Lock lock = new ReentrantLock();

    public static void increase() {
        lock.lock();
        sharedResource++;
        lock.unlock();
    }

    public void show() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new CustomThread());
            threads.add(thread);
            thread.start();
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(sharedResource);
    }
}
