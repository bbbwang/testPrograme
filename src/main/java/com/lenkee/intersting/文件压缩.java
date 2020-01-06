package com.lenkee.intersting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by amettursun on 2019/12/22.
 * 参考 https://blog.csdn.net/haqiang555/article/details/83988153
 */
public class 文件压缩 {

    public static void main(String args[]) {
        System.out.println("压缩测试");
        String path = "/Users/amettursun/dashboard_data/image/download/2019122218598/sdfdsa";
//        ArrayList<File> files = getFiles(path);
//        Map<String,String> bases = new HashMap<>();
//        for (int i = 0; i < files.size(); i++) {
//            File s =  files.get(i);
//            String value = s.getPath().substring(s.getPath().indexOf("download")+1); // 转为相对路径
//            bases.put(s.getAbsolutePath(), value);
//        }
//        File zip = new File("/Users/amettursun/dashboard_data/image/download/17d0d818-557b-4d5d-8794-d8852b1b5525.zip");
//        zipFileFolders(files,zip,bases);
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 读取某个目录下所有文件、文件夹
     * @param path
     * @return
     */
    public static ArrayList<File> getFiles(String path) {
        ArrayList<File> files = new ArrayList<File>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i]);
            }
            if (tempList[i].isDirectory()) {
                ArrayList<File> files1 = getFiles(tempList[i].getPath());
                files.addAll(files1);
            }
        }
        return files;
    }

    /**
     * 功能:压缩多个文件，文件夹成一个zip文件
     * @param srcfile：源文件列表
     * @param zipfile：压缩后的文件
     * @param bases：文件对应的压缩路径
     */
    public static void zipFileFolders(List<File> srcfile, File zipfile, Map<String,String> bases) {
        byte[] buf = new byte[1024];
        ZipOutputStream out = null;
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.size(); i++) {
                FileInputStream in = new FileInputStream(srcfile.get(i));
                String filePath = bases.get(srcfile.get(i).getAbsolutePath());
                if(filePath==null)
                    filePath = "";
                out.putNextEntry(new ZipEntry(filePath));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            System.out.println("压缩完成.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
