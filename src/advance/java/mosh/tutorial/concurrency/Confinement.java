package advance.java.mosh.tutorial.concurrency;

// Confinement

import java.util.ArrayList;
import java.util.List;

class MyThread implements Runnable {
    private int resource;

    MyThread () {
        this.resource = 0;
    }

    private void increase() {
        this.resource++;
    }

    public int getResource() {
        return resource;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 1000; i++) {
            increase();
        }
    }
}
public class Confinement {
    public void show() {
        List<Thread> threads = new ArrayList<>();
        List<MyThread> tasks = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            MyThread task = new MyThread();
            tasks.add(task);
            Thread thread = new Thread(task);
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

        var result = tasks.stream()
                .map(MyThread::getResource)
                .reduce(Integer::sum);

        result.ifPresent(System.out::println);
    }
}
