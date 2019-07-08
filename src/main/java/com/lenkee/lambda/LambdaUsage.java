package com.lenkee.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongPredicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by amettursun on 2019/5/8.
 */
public class LambdaUsage {
    public static void main(String[] args) {

        // list中有3个不同颜色重量的苹果
        List<Apple> list = Arrays.asList(new Apple("green",120), new Apple("red",150),new Apple("yellow",80));

        // filterByWeight方法中筛选 质量大于100的苹果 条件
        List<Apple> result = filterByWeight(list, w -> w > 100);
        // 或者用 用 stram filter 条件过滤， filter里填写Predict类型
        List<Apple> result2 = list.stream().filter(p -> p.getWeight() > 100).collect(Collectors.toList());

        String result3 = testFunction(new Apple("yellow", 110), a -> a.toString());


        System.out.println("result 1:"+result);
        System.out.println("result 2:"+result2);
        System.out.println("result 3:"+result3);

//        Supplier<String> str = String::new;
//        System.out.println(str.get());


        Apple green = createApple(() -> new Apple("Green", 100));
        System.out.println("green:"+ green);

        int i = 0;
        Runnable r = () -> System.out.println("Runnable:"+i);  // 默认匿名内部类
        r.run();

    }

    // 条件筛选出苹果
    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getWeight()))
                result.add(a);
        }
        return result;
    }


    private static String testFunction(Apple apple, Function<Apple, String> fun){
        return fun.apply(apple);
    }

    private static Apple createApple (Supplier<Apple> supplier){
        return supplier.get();
    }

}

