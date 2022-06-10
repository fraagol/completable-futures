package org.javi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture =  CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        System.out.println("CompletableFuture object created");
        System.out.println("CompletableFuture get() call ");
        CompletableFuture<Void> voidCompletableFuture = completableFuture
                .thenApply(s -> {
                    System.out.println("Inside thenApply: " + s);
                    return s.toUpperCase();
                })
                .thenAccept(s1 -> {
                    System.out.println("Inside thenAccept: " + s1);
                });
        CompletableFuture<String> completableFutureSecond = completableFuture.thenApply(s -> {
            System.out.println("Inside second thenApply: " + s);
            return "second";
        });


        voidCompletableFuture.get();
        System.out.println("CompletableFuture get call returned");
    }
}
