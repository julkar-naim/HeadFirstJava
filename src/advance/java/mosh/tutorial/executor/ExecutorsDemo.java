package advance.java.mosh.tutorial.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class ExecutorsDemo {
    public void show() {
        var executor = Executors.newFixedThreadPool(2);

        Callable<String> thread = () -> {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
            return "returned";
        };
        Supplier<String> supplier = () -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "returned";
        };

        try {
            var result = CompletableFuture.supplyAsync(supplier);
            var result2 = CompletableFuture.supplyAsync(supplier);
            System.out.println(result.get());
            System.out.println(result2.get()); // blocking nature
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
