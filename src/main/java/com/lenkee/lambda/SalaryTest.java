package com.lenkee.lambda;

/**
 * Created by amettursun on 2019/6/25.
 */
public class SalaryTest {
    public static void main(String[] args) {
        int month = 12;
        double preSalary = 10000;
        System.out.println(monthSalary(month,preSalary));
    }
    private static double monthSalary(int month, double preSalary){
        if (month ==1){
            double head = (preSalary * month - 5000 * month - 4000 * month-2000*month);
            Caculate grad = grad(head);
            double result =  head * grad.getRadio() - grad.getSubtraction();
            return result;
        }
        int last = month;
        if (month>2)
            last = 3;
        double head = (preSalary * month - 5000 * month - 4000 * month-2000*month);
        Caculate grad = grad(head);
        double result =  head * grad.getRadio() - grad.getSubtraction()-monthSalary(month-1,preSalary)*(last-1);
        return result;
    }

    private static Caculate grad(double money){
        Caculate map ;
        if (money<36000){
             map = new Caculate(3.0/100,0.0);
            return map;
        }
        if (money<144000){
            map = new Caculate(10.0/100,2520);
            return map;
        }
        if (money<300000){
            map = new Caculate(20.0/100,16920);
            return map;
        }
        if (money<420000){
            map = new Caculate(25.0/100,31920);
            return map;
        }
        if (money<660000){
            map = new Caculate(30.0/100,52920);
            return map;
        }
        if (money<960000){
            map = new Caculate(35.0/100,85920);
            return map;
        }else{
            map = new Caculate(45.0/100,181920);
            return map;
        }

    }

}
class Caculate{
    public Caculate(double radio, double subtraction) {
        this.radio = radio;
        this.subtraction = subtraction;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getSubtraction() {
        return subtraction;
    }

    public void setSubtraction(double subtraction) {
        this.subtraction = subtraction;
    }

    double radio;
    double subtraction;
}
