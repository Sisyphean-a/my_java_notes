package com.fusi.test08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ReadLineTest {

    public static int line(String path){
        int lineNum = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            while (null != br.readLine()) {
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineNum;
    }

    public static Collection<String> pathArr(Collection<String> co ,String path){
        File f = new File(path);
        if (f.exists()){
            File[] files = f.listFiles();
            assert files != null;
            for (File fi : files){
                if (fi.isFile()){
                    co.add(fi.getAbsolutePath());
                } else {
                    pathArr(co ,fi.getAbsolutePath());
                }
            }
        }
        return co;
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\xiakn\\IdeaProjects\\javase\\src\\com\\fusi";
        Collection<String> co = new ArrayList<>();
        int lineAll = 0;
        Collection<String> coll = pathArr(co , path);
        for (String s : coll) {
            System.out.println(s);
            int li = line(s);
            lineAll += li;
            System.out.println("当前代码总行数：" + lineAll + " ,当前文件行数：" + li);
        }
    }
}
