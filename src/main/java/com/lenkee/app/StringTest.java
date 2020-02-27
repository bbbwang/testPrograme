package com.lenkee.app;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by amettursun on 2019/7/4.
 */
public class StringTest {
    private int[] vals = {1,2,3,4,5};
    public static void main(String[] args) {
        String summary = "LLT-38470 Android 5.41.0 Diagnostics";
        System.out.println(getSummaryTicket(summary));
    }

    private static void dealSearchData(String str){
        String key = str.substring(0,str.lastIndexOf("["));
        String num = str.substring(str.lastIndexOf(".")+1,str.length()-1);
        System.out.println(key+":"+num);
    }

    private static String getSummaryTicket(String summary){

        String head = "LLT-";
        StringBuffer ticket = new StringBuffer();
        if (summary.startsWith(head)){
            for (int i = head.length(); i < summary.length(); i++) {
                char c = summary.charAt(i);
                if (Character.isDigit(c)){
                    ticket.append(c);
                }else {
                    return ticket.toString();
                }
            }
        }
        return head + ticket.toString();
    }


    public static String getFilePath(String description){
        try {
            Pattern p=Pattern.compile("((\\w+.4cb)|(\\w+.properties)|(\\w+.xml))");
            Matcher matcher = p.matcher(description);
            while (matcher.find()) {
                String group = matcher.group();
                System.out.println(group);
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }



    public static void fooo(){
        try {
            return;
        }finally {
            System.out.println("finally");
            return;
        }
    }
    public static String result="";
    public static void foo(int i){
        try {
            if (i==1){
                throw new Exception();
            }
            result += "a";
        } catch (Exception e) {
            result +="b";
            return;
        }finally {
            result +="c";
        }
        result +="d";
    }
    public static String output = "";

    public static int getFib(int n){
        if(n < 0){
            return -1;
        }else if(n == 0){
            return 0;
        }else if(n == 1 || n ==2){
            return 1;
        }else{
            return getFib(n - 1) + getFib(n - 2);
        }
    }


    public static void getDate(Date date){
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        SimpleDateFormat format = new SimpleDateFormat();
        String monthfirst = format.format(c.getTime());
        System.out.println("nowfirst:" + monthfirst);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String monthlast = format.format(ca.getTime());
        System.out.println("last:" + monthlast);
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
            return asList(original.split(","));

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
            List<String> list = asList(arrayResult);
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




    public static void countWord(String str){
        Map<String, Integer> map = new HashMap<>();
        String[] data = str.split(" ");
        for (String word : data) {
            map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
        }
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }

    public static void buyPen(){
        for (int x = 0; x <100 ; x++) {
            for (int y = 0; y < 100; y++) {
                if ((5*x+3*y)==100){
                    int z=100-x-y;
                    if (z>=0&&z<=100){
                        System.out.println("钢笔："+x+"圆珠笔："+y+"铅笔："+z);
                    }
                }
            }
        }
    }

    // 去除list中重复字符串
    public static List<String> removeDuplicate(List<String> list){
        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        return collect;
    }

    public static void mapTest(){
        Map<String, Integer> map = new Hashtable<>();
        map.put("Pen", 10);
        map.put("Book", 500);
        map.put("Clothes", 400);
        map.put("Mobile", 5000);

        System.out.println("hashTable: "
                + map.toString());

        // 为缺少的新key提供值
        // 使用computeIfAbsent方法
        map.computeIfAbsent("newPen", k -> 600);
        map.computeIfAbsent("newBook", k -> 800);
        map.computeIfAbsent("Mobile", k -> 8001);

        System.out.println("new hashTable: "
                + map);
    }
    public static void sortMap(List<Map<String,String>> maps){
        List<Map<String, String>> collect = maps.stream().sorted((a, b) -> {
            return a.get("assignee").compareTo(b.get("assignee"));
        }).collect(Collectors.toList());
        collect.stream().forEach(m->{
            System.out.println(m.get("assignee"));
        });
    }

    // 正则匹配
    private static String regx(String str){
        Pattern PLURALS_KEY = Pattern.compile("<plurals name=\"([a-z0-9_-]+)\"");
        Matcher matcherKey = PLURALS_KEY.matcher(str);
        if (matcherKey.find()) {
            return matcherKey.group(1);
        }
        System.out.println("not find");
        return null;
    }


}


