package com.lenkee.lambda;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by amettursun on 2019/6/14.
 */
public class CompletableFutureTest {
    private static Random RANDOM = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture();
        new Thread(()->{
            double value = get();
            completableFuture.complete(value);
        }).start();
       // Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
        completableFuture.whenComplete((v, t) ->{
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(t1 -> t1.printStackTrace());
        });
        System.out.println("====no====block====");
    }
    public static double get(){
        int i = RANDOM.nextInt(10000);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i/1000.0;
    }
}