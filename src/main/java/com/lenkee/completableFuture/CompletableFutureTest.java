package com.lenkee.completableFuture;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by amettursun on 2019/6/24.
 */
public class CompletableFutureTest {

    @Test
    public static void test1() throws ExecutionException, InterruptedException {
        // 创建CompletableFuture
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        // 手工的完成一个 Future
        completableFuture.complete("Future's Result");

        // 直接获取结果,get() 方法会一直阻塞直到 Future 完成
        String result = completableFuture.get();

    }

    // 使用 runAsync() 运行异步计算
    @Test
    public static void test2() throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("三秒钟后执行");
//            }
//        });

        // lambda 表达方式传入Runnable
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("三秒钟后执行");
        });
        // 阻塞直到得到返回结果
        future.get();
        System.out.println("代码顺序执行完。。");
    }

    // CompletableFuture.supplyAsync方式运行一个异步任务并且返回结果
    @Test
    public static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                String data = "三秒钟后执行";
                System.out.println(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "三秒钟后执行";
        });
        // 阻塞直到返回结果
        String result = future.get();
        System.out.println("代码顺序执行完");
    }

    // 创建一个线程池，并传递给其中一个方法
    @Test
    public static void test4() {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "三秒钟后执行";
        }, executor);
    }

    // 使用 thenApply() 处理和改变CompletableFuture的结果
    @Test
    public static void test5() throws ExecutionException, InterruptedException {
        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Devin";
        });

        // future执行完成后自动回调
        CompletableFuture<String> greentingFuture = nameFuture.thenApply(name -> {
            return "hello " + name;
        });
        System.out.println(greentingFuture.get());
        System.out.println("程序顺序执行完毕。。");
    }

    @Test
    public static void test6() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("执行一个程序");
            return "Some Result";
        }).thenApplyAsync(result -> {
            // Executed in a different thread from ForkJoinPool.commonPool()
            System.out.println("异步回调程序");
            return "Processed Result";
        });

        System.out.println("程序顺序执行完");

    }

    // 使用 thenCompose()组合两个独立的future
    @Test
    public static void test7() throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("sleep 3 seconds...");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("sleep 3 seconds...");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm / 100;
                    return weightInKg / (heightInMeter * heightInMeter);
                });

        System.out.println("Your BMI is - " + combinedFuture.get());
        System.out.println("程序顺序执行完");

    }

    // 组合多个CompletableFuture
    @Test
    public void test8() throws ExecutionException, InterruptedException {

        List<String> webPageLinks = Arrays.asList("page 1","page 2","page 3","page 4","page 5")   ; // A list of 100 web page links

        // Download contents of all the web pages asynchronously
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());


        // Create a combined Future using allOf()
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()])
        );

        /**
         When all the Futures are completed,
         call `future.join()` to get their results and collect the results in a list -
         *
         */
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures.stream()
                    .map(pageContentFuture -> pageContentFuture.join())
                    .collect(Collectors.toList());
        });
        System.out.println("allPageContentsFuture: "+allPageContentsFuture.get());

        // Count the number of web pages having the "CompletableFuture" keyword.
        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
            return pageContents.stream()
                    .filter(pageContent -> pageContent.contains("page 3"))
                    .count();
        });

        System.out.println("Number of Web Pages having CompletableFuture keyword : " +
                countFuture.get());



    }

    CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            // Code to download and return the web page's content
            return pageLink+" downloaded";
        });
    }

    // CompletableFuture 异常处理
    @Test
    public void test9() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());

    }


}
