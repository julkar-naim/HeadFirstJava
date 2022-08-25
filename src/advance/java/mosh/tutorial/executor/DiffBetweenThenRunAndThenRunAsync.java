package advance.java.mosh.tutorial.executor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DiffBetweenThenRunAndThenRunAsync {
    public CompletableFuture<Void> task1() {
        return CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Executing task 1..... Thread name: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public Runnable afterTask() {
        return () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Executing after task.... Thread name: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public void testThenRun() {
        task1().thenRun(afterTask());
    }

    public void testThenRunAsync() {
        task1().thenRunAsync(afterTask());
    }
}
