package com.lenkee.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by amettursun on 2019/5/22.
 */
public class OptionalTest {
    public static void main(String[] args) {
        // 创建一个空对象果篮
        Optional<Basket> basket1  = Optional.empty();//Optional.of(new Basket("bamboo-basket",10,null));
        // 创建一个非null的果篮Optional
        Optional<Basket> basket2  = Optional.of(new Basket("bamboo-basket",10, Arrays.asList(new Apple("red",120),new Apple("blue",100))));

        // 获取basket
        Basket bsk = basket2.get();
        // 判断果篮是否为空值果篮
        System.out.println("basket:"+basket1.isPresent());

        // 判断果篮是否为空，不为空就输出果篮
        if (basket2.isPresent())
            System.out.println(basket2.toString());

        //  如果这个篮子不为空，执行lambda表达式，反之空的就不会执行
        basket1.ifPresent((bask)->{
            System.out.println("这个篮子是："+bask.toString());
        });

        // 如果对象为空，则将它指定为一个塑料篮子
        List<Apple> list = Arrays.asList(new Apple("red",200), new Apple("blue",220), new Apple("yellow",210));
        // 原来不存在，所以给它一个新的值
        Basket basket11 = basket1.orElse(new Basket("plastic-basket", 10, list));
        System.out.println("new basket11:"+basket11.toString());
        // 原来存在，直接赋原来值
        Basket basket21 = basket2.orElse(new Basket("plastic-basket", 10, list));
        System.out.println("new basket21:"+basket21.toString());

        // 如果果篮为空，抛出异常
        //basket1.orElseThrow(IllegalStateException::new);

        // 判断出有苹果的果篮
        basket2.filter(basket -> {return basket.getList().size()>0;}).ifPresent((basket)->{
            System.out.println("果篮里有水果");
        });

    }
}

// 水果篮
class Basket{
    private String name;
    private int size;
    private List<Apple> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Apple> getList() {
        return list;
    }

    public void setList(List<Apple> list) {
        this.list = list;
    }

    public Basket(String name, int size, List<Apple> list) {
        this.name = name;
        this.size = size;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", list=" + list +
                '}';
    }
}
