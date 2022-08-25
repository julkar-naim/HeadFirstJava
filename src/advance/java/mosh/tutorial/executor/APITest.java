package advance.java.mosh.tutorial.executor;

import java.util.concurrent.ExecutionException;

public class APITest {
    public void show() {
        API api = new API();
        //            api.blockingYou().get();
        api.nonBlockingNature();
        System.out.println("not blocking");
        //            System.out.println("I am getting blocked from execution :(");
    }
    /*
    Running code on completion
    case 1: Run A then run B
    case 2: Run A, get the result from A and input the result in B, then run B
     */

    public void runAfterCompletion() {
        API api = new API();

        api.nonBlockingNature().thenRun(() -> {
            System.out.println("After task at: " + Thread.currentThread().getName() + " First");
        });

        var future = api.asyncButReturnsValue();
        future.thenRunAsync(() -> {
            System.out.println("After task... at " + Thread.currentThread().getName() + " Second");
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void runAndProvideValue() {
        API api = new API();
        var future = api.asyncButReturnsValue();
        future.thenAccept(result -> {
            System.out.println("After: " + Thread.currentThread().getName());
            System.out.println(result);
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
