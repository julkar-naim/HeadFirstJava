package advance.java.mosh.tutorial.executor;

import java.util.concurrent.CompletableFuture;

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
                .thenAcceptAsync(ComposingCompletableFuture::getPlayListAsync) // yes we need compose because thenApplyAsync return a Void CF object
                .thenAccept(System.out::println);
    }
}
