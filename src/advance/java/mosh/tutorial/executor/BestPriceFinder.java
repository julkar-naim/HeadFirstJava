package advance.java.mosh.tutorial.executor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;

record Quote(String site, int price) {} // wow, just learned something awesome. Java 14 has a special keyword for data class. which is called 'record'
// compiler generates getter, setter, toString, hashCode automatically for us :D

public class BestPriceFinder {
    public void takeTime(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void find() {

        Function<String, CompletableFuture<Quote>> getSite = site -> CompletableFuture.supplyAsync(() -> {
            var time = (long)(1000 + (Math.random() * 10000 % 4000));
            System.out.println("Getting a quote from " + site);
            takeTime(time);
            return new Quote("site1", (int)(Math.random() * 1000 % 200));
        });

        var startTime = LocalTime.now();

        CompletableFuture.allOf(
            getSite.apply("site1").thenAccept(System.out::println),
            getSite.apply("site2").thenAccept(System.out::println),
            getSite.apply("site3").thenAccept(System.out::println)
        ).thenRun(() -> {
            var duration = Duration.between(startTime, LocalTime.now());
            System.out.println("Retrieved all quotes in " + duration.toMillis() + " msec");
        });
    }

}
