package com.lenkee.app;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by amettursun on 2019/7/4.
 */
public class StringTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String , String> map = new HashMap();
        map.put("11","v11");
        map.put("22","v22");
        map.put("33","v33");
        for (String s:
             map.values()) {
            System.out.println(s);
        }

    }

    public static void function(String str, int i) throws UnsupportedEncodingException {
        int num = 0;
        byte[] b = str.getBytes("gbk");//默认UTF-8 中文一般三个字节表示，gbk两个字节 变化就是%3
        if(b[i-1] > 0){
            System.out.println(new String(b,0,i,"gbk"));
        }
        else{
            for(int j = 0; j < i; j++){
                if(b[j] < 0){
                    num++;
                    num = num % 2;
                }
            }
            System.out.println(new String(b,0,i-num,"gbk"));
        }
    }

    private static  String generateLqaTicket(String lqaTicket, String allStrings) {
        if (allStrings==null || allStrings.isEmpty())
            return lqaTicket;
        final String[] newString = {""};
        if (lqaTicket!=null && !lqaTicket.isEmpty())
            newString[0] = lqaTicket+",";

        String[] split = allStrings.split(",");
        Arrays.stream(split).filter(s -> !s.equals(lqaTicket)).forEach(s -> {
            newString[0] += "$"+s+",";
        });
        String s = newString[0];
        if (s.endsWith(",")||s.endsWith("$"))
            s = s.substring(0,s.length()-1);
        return s;
    }

    public static List<String> removedTickets(String original, String optional) {
        if (original ==null || original.isEmpty())
            return new ArrayList<>();
        if (optional == null || optional.isEmpty())
            return Arrays.asList(original.split(","));

        List<String> removed = new ArrayList<>();
        String[] pre = original.split(",");
        String[] now = optional.split(",");
        if (optional != null){
            for (int i = 0; i < pre.length; i++) {
                boolean delete = true;
                for (int j = 0; j < now.length; j++) {
                    if (pre[i].equals(now[j])){
                        delete = false;
                        break;
                    }
                }
                if (delete)
                    removed.add(pre[i]);
            }
        }
        return removed;
    }

    private static String deduplication(String ticket){
        if (ticket != null && "".equals(ticket)){
            String[] array = ticket.split(",");
            Set<String> set = new HashSet<>();
            for(int i=0;i<array.length;i++){
                set.add(array[i]);
            }
            String[] arrayResult = (String[]) set.toArray(new String[set.size()]);
            String result = "";
            for (int i = 0; i < arrayResult.length; i++) {
                result += arrayResult[i]+",";
            }
            if (result.endsWith(","))
                result = result.substring(0,result.length()-1);
            return result;
        }
        return ticket;
    }
}
