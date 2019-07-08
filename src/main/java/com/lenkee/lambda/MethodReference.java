package com.lenkee.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by amettursun on 2019/5/16.
 */
public class MethodReference {
    public static void main(String[] args) {
        useConsumer(s -> System.out.println(s), "Hello wang");
        useConsumer(System.out::println, "Hello qiang");  // 两个冒号表示直接获取值

        List<Apple> list = Arrays.asList(new Apple("green",100), new Apple("blue",130), new Apple("yellow",120));
        list.sort((a1,a2) -> a1.getColor().compareTo(a2.getColor())); // 名字从小到大排列
        System.out.println(list);
//        list.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
//        System.out.println(list);

        list.stream().forEach(a -> System.out.println(a));
        list.stream().forEach(System.out::println);  // 等价于上一行，自动推导出传入参数

        Consumer<Integer> fun = Apple::fun;
        fun.accept(100);
        // 1一个类里有静态方法；
        Function<String, Integer> function = Integer::parseInt;
        Integer apply = function.apply("123");
        System.out.println(apply);
        // 2 用一个实例方法/类方法；
        BiFunction<String, Integer, Character> function2 = String::charAt;
        Character c = function2.apply("hello", 2);
        System.out.println(c);
        // 3 存在的对象的一个实例方法推断
        String string = new String(("Hello"));
        Function<Integer, Character> function3 = string::charAt;
        Character c2 = function3.apply(4);
        System.out.println(c2);

        // 构造函数推导
        Supplier<String> supplier = String::new;
        String s = supplier.get();
        System.out.println(s);

        BiFunction<String, Integer, Apple> appFun = Apple::new;  // 它会去找对应的构造函数
        Apple apple = appFun.apply("red", 100);
        System.out.println(apple);

        Consumer<Apple> ap = a -> System.out.println("the color of "+a.getColor()+" apple have"+ a.getWeight()+" kg");
        ap.accept(new Apple("red",100));


    }

    private static <T> void useConsumer(Consumer<T> consumer, T t){
        consumer.accept(t);
        consumer.accept(t);
    }

}


