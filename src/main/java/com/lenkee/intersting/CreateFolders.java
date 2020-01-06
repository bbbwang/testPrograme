package com.lenkee.intersting;

import java.io.File;
import java.util.Scanner;

/**
 * Created by amettursun on 2019/10/8.
 */
public class CreateFolders {
    public static void main(String[] args) {
        System.out.println("please input caseId:");
        Scanner input = new Scanner(System.in);
        String ticketName = input.next();
        String filePath = "/Users/amettursun/Desktop/"+ticketName;
        String[] locals = {"de_DE","en_AU","en_CA","en_GB","es_ES","fr_CA","fr_FR","it_IT","pt_BR","es_CO","nl_BE","nl_NL","zh_HK","pl_PL","ru_RU","zh_CN"};
        File ticket = new File(filePath);
        if (! ticket.exists() || !ticket.isDirectory()){
            ticket.mkdir();
            for (int i = 0; i < locals.length; i++) {
                String local = filePath +"/"+ locals[i];
                File folder = new File(local);
                folder.mkdir();
            }
        }

    }

}
