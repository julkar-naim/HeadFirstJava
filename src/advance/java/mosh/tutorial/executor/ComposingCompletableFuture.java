package advance.java.mosh.tutorial.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ComposingCompletableFuture {

    public static CompletableFuture<String> getEmailAsync() {
        return CompletableFuture.supplyAsync(() -> "Email");
    }

    public static CompletableFuture<String> getPlayListAsync(String email) {
        return CompletableFuture.supplyAsync(() -> "Playlist");
    }

    public void show() { // composing
        getEmailAsync()
                .thenCompose(ComposingCompletableFuture::getPlayListAsync)
                .thenAccept(System.out::println);
    }

    public void doWeReallyNeedCompose() {
        getEmailAsync()
                .thenApplyAsync(ComposingCompletableFuture::getPlayListAsync)
                .thenApplyAsync(cf -> cf.getNow(""))
                .thenAccept(System.out::println);
    }
    // Okay, I was wrong. It is possible to do composing without thenCompose. But it makes the method chaining longer
}
