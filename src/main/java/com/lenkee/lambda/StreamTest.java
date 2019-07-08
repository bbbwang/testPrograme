package com.lenkee.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * 当我们使用一个流的时候，通常包括三个基本步骤：
 获取一个数据源（source）→ 数据转换→执行操作获取想要的结果，
 每次转换原有 Stream 对象不改变，返回一个新的 Stream 对象（可以有多次转换），
 这就允许对其操作可以像链条一样排列，变成一个管道
 */
public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
        /**
         *  最常见的几种构造stream
         */

        // map 平方数,这里的map是一种映射关系，每输入一个元素，都按照规则转换成另外一个元素
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).
                collect(Collectors.toList());
        System.out.println("平方数："+squareNums);

        // filter 筛选出偶数，通过筛选留下来满足规则都元素
        List<Integer> test = list.stream().filter(integer -> {
            return integer % 2 == 0;
        }).collect(Collectors.toList());
        System.out.println(test);

        // 倒排序
        List<Integer> test2 = list.stream().sorted((a, b) -> {
            return b-a;
        }).collect(Collectors.toList());
        System.out.println("test2:"+test2);

        // Match，这里有3种情况
        /**
         * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
         * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
         * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
         */
        // 全都是偶数
        boolean b = list.stream().allMatch(integer -> {
            return integer % 2 == 0;
        });
        System.out.println("全部是偶数："+b);
        // 存在偶数
        b = list.stream().anyMatch(integer -> {
            return integer % 2 == 0;
        });
        System.out.println("存在偶数："+b);
        // 都不是偶数
        b = list.stream().noneMatch(integer -> {
            return integer % 2 == 0;
        });
        System.out.println("都不是偶数："+b);

        //  forEach ，每个数字前加上一个"*"
        System.out.println("每个数字前加上一个\"*\":");
        list.stream().forEach(integer -> System.out.print("*"+integer+" "));
        System.out.println("");

        /**
         * 这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），
         * 然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
         * 从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。
         */
        // reduce
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("1:"+concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("2:"+minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println("3:"+sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("4:"+sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println("5:"+concat);
    }
}
