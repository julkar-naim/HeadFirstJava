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
}
