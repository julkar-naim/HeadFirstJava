import advance.java.mosh.tutorial.concurrency.Concurrency;
import advance.java.mosh.tutorial.concurrency.Confinement;
import advance.java.mosh.tutorial.concurrency.Locking;
import advance.java.mosh.tutorial.executor.*;

public class Main {
    public static void main(String[] args) {
        new BestPriceFinder().find();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
