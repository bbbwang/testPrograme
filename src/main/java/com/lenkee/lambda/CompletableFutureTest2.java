package com.lenkee.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

/**
 * Created by amettursun on 2019/6/14.
 */
public class CompletableFutureTest2 {
    private static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2, r->{
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });
        List<Integer> productionIds = Arrays.asList(1,2,3,4,5);

        List<Double> result = productionIds
                .stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> queryProductions(integer), executor))
                .map(future -> future.thenApply(CompletableFutureTest2::multiply))
                .map(future -> future.thenApply(CompletableFutureTest2::multiply))
                .map(CompletableFuture::join).collect(toList());

        System.out.println(result);

    }

    private static double queryProductions(Integer integer) {
        System.out.println("query id:"+integer);
         return CompletableFutureTest.get();
    }

    private static double multiply(double value){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value*10d;
    }

    public static double get(){
        int i = RANDOM.nextInt(1000);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i/1000.0;
    }
}