package com.lenkee.app;

/**
 * Created by amettursun on 2019/10/21.
 */
public class Testing {
    private  static final int n = 10;

    public static void main(String[] args) {
        suClassP s = new suClassP();
        AA a1 = (AA) new suClassP();

    }


}

class suClassP{}
class AA extends suClassP{}
class BB extends  suClassP{}


