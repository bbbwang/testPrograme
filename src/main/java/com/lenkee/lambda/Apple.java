package com.lenkee.lambda;

/**
 * Created by amettursun on 2019/5/13.
 */
public class Apple {
    String color;
    Integer weight;

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void fun(Integer integer){
        System.out.println("this is static function: " + integer);
    }
}
