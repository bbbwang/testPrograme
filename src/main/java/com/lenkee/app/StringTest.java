package com.lenkee.app;

import java.io.IOException;
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
    public static void main(String[] args) throws IOException {

//        List<Map<String,String>>  list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Map<String,String> map = new HashedMap();
//            Map<String,String> map2 = new HashedMap();
//            map.put("assignee","assignee"+Math.random());
//            map2.put("assignee","");
//            list.add(map);
//            list.add(map2);
//        }
//        List<Map<String,String>> start = list.stream().filter(m -> {
//            Map<String, String> m1 = (Map<String, String>) m;
//            return !"".equals(m1.get("assignee"));
//        }).collect(Collectors.toList());
//        List<Map<String,String>> end = list.stream().filter(m -> {
//            Map<String, String> m1 = (Map<String, String>) m;
//            return !"".equals(m1.get("assignee"));
//        }).collect(Collectors.toList());
//        sortMap(start);
//        dida();
        String str = "Issue:\n" +
                "Change string for AU - Remove date format\n" +
                "\n" +
                "Source:\n" +
                "Seller Registration Date (MMM dd, yyyy)\n" +
                "\n" +
                "Actual:\n" +
                "Seller registration date (MMM dd, yyyy)\n" +
                "\n" +
                "Expected result:\n" +
                "Seller registration date\n" +
                "\n" +
                "SID:\n" +
                "sarrptbat.report_builder.registrationInfo.sellerRegDate\n" +
                "\n" +
                "File:\n" +
                "/L10N/Raptor/source/en/request77492/LPP-P-53515.LVE-JG-63763/content/v4contentsource/source/en/sarrptbat/report_builder.4cb\n" +
                "\n" +
                "L10NHub ID:53515\n" +
                "\n" +
                "File path:53515: https://github.corp.ebay.com/ACCOUNT-SETTINGS/sarrptbat/tree/report_builder_ccpa/content/v4contentsource/source/en/sarrptbat/report_builder.properties\n" +
                "\n" +
                "Step ID:3\n" +
                "Reported by Stacey, Isabel(AWF)\n" +
                "LQA Review Page";
        System.out.println(getFilePath(str));


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


    public static void modify(String s){
        s += "world";
    }

    public static int fun(int n){
        if(n ==1){
            return 1;
        }
        return fun(n-1)*n;
    }

    public static void multiplicationTable(){
        for(int i=9;i>0;i--) {
            for(int j=i;j>0;j--) {
                System.out.print(j+"×"+i+"="+i*j+"\t");// \t 跳到下一个TAB位置
            }
            System.out.println();
        }
    }

    public static String cutString(String str){
        return "Some of the tickets do not exist. Please check it: "+str.substring(str.lastIndexOf(" Wrong ticket(s): "));
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

    public static void dida(){
        String str = "-... -.- -.-. - ..-. -- .. ... -.-.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i,i+1).equals("-")){
                sb.append("1");
            }else
            if (str.substring(i,i+1).equals(".")){
                sb.append("0");
            }else {
                sb.append(str.substring(i,i+1));
            }
        }
        System.out.println(sb.toString());
    }

    public static void fuun(){
        String str= "wng,qiang,helo,nihao";
        String[] split = str.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }

    public static void operate(String x, String y){
        y+=x;
        y=x;
    }


}
abstract class abstractIt{
    abstract float getFload();
}


