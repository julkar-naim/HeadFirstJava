package advance.java.mosh.tutorial.executor;

import java.util.concurrent.*;

public class API {
    public void blockingNature() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            var future1 = executor.submit(() -> {
                Thread.sleep(5000);
                return 1;
            });
            var future2 = executor.submit(() -> {
                Thread.sleep(1000);
                return 2;
            });

            System.out.println(future1.get());
            System.out.println("Interval");
            System.out.println(future2.get());
            // so we can see the second thread takes less time but still waits for the first thread to complete.

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }

    public void nonBlockingNature() {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Complete");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
