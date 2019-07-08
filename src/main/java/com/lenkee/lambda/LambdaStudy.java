package com.lenkee.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by amettursun on 2019/5/8.
 */
public class LambdaStudy {
    public static void main(String[] args) {
        // 普通表达式
        Comparator<Apple> byColor = new Comparator<Apple>() {
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        // Lambda表达式
        Comparator<Apple>  byColor2 = (o1,o2) -> o1.getColor().compareTo(o2.getColor());
        List<Apple> list = Collections.emptyList();
        list.sort(byColor);
        list.stream().forEach(apple -> {
            System.out.println(apple);
        });

        Function<String, Integer> flambda = s -> s.length();

        Predicate<Apple> p =  a -> a.getColor().equals("green");  // 接收一个东西，返回一个boolean值

        Fruit intSupplier = () ->{  System.out.println("******* ball ********");};

    }


}

