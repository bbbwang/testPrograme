package com.lenkee.app;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by amettursun on 2019/7/4.
 */
public class StringTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String str = "11,2,33, 44, 555,$$,$11,$2,$333,$5,$,";
//        String now = "22,33,43,54,$$,$,13,$333";
//        //System.out.println(deduplication(str));
//        List<String> list = removedTickets(str, now);
//        // 输出所有list
//        System.out.println(Arrays.toString(list.toArray()));
        String url = "https://github.corp.ebay.com/localization/pseudo-service";
        System.out.println(httpsToSsh(url));

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
        if (lqaTicket!=null && !lqaTicket.trim().isEmpty())
            newString[0] = lqaTicket.trim()+",";

        String[] split = allStrings.split(",");
        Arrays.stream(split).filter(s -> !s.trim().equals(lqaTicket.trim())).forEach(s -> {
            newString[0] += "$"+s.trim()+",";
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

    private static   String deduplication(String ticket){
        if (ticket != null && !"".equals(ticket)){
            String[] array = ticket.split(",");
            Set<String> set = new HashSet<>();
            for(int i=0;i<array.length;i++){
                set.add(array[i]);
            }
            String[] arrayResult = (String[]) set.toArray(new String[set.size()]);
            String result = "";
            List<String> list = Arrays.asList(arrayResult);
            List<String> sortList1 = list.stream().filter(l->!l.startsWith("$")).collect(Collectors.toList());
            List<String> sortList2 = list.stream().filter(l->l.startsWith("$")).collect(Collectors.toList());
            List<String> sortList = new ArrayList<>();
            sortList.addAll(sortList1);
            sortList.addAll(sortList2);
            for (int i = 0; i < sortList.size(); i++) {
                String temp = sortList.get(i);
                if (temp.startsWith("$")) {
                    if (list.contains(temp.substring(1)))
                        continue;
                }
                result += temp+",";
            }
            if (result.endsWith(","))
                result = result.substring(0,result.length()-1);
            return result;
        }
        return ticket;
    }

    public static String httpsToSsh(String url){
        String baseUrl = "github.corp.ebay.com";
        try{
            String[] split = url.split(baseUrl+"/");
            String content = split[split.length-1];
            return "git@"+baseUrl+":"+content+".git";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
