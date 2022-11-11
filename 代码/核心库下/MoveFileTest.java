package com.fusi.test20;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *   a. 使用线程池将一个目录中的所有内容拷贝到另外一个目录中，包含子目录中的内容。
 *   b.实现将指定目录中的所有内容删除，包含子目录中的内容都要全部删除。
 */

public class MoveFileTest implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }

    public static File create(File file){
        String str = file.getAbsolutePath();
        String[] split = str.split("\\\\");
        StringBuilder str2 = new StringBuilder("D:");
        for (int i = 1 ; i < split.length ; i++ ){
            str2.append("\\").append(split[i]);
        }
        File fi = new File(String.valueOf(str2));
        if (!fi.getParentFile().exists()){
            fi.getParentFile().mkdirs();
        }
        try {
            fi.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fi;
    }

    public static void copy(File file) {
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File file1 = create(file);
        try {
            bis = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
            bos = new BufferedOutputStream(new FileOutputStream(file1.getAbsolutePath()));
            byte[] bArr = new byte[1024];
            int res = 0;
            while ((res = bis.read(bArr)) != -1) {
                bos.write(bArr, 0, res);
            }
            System.out.println(file.getAbsolutePath() + "复制成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bos != null;
                bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ifElse(File file, ExecutorService es) {
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files){
            if (f.exists()) {
                if (f.isFile()) {
                    es.execute(new Runnable() {
                        @Override
                        public void run() {
                            copy(f);
                        }
                    });
                } else {
                    ifElse(f,es);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 1. 判断是文件还是文件夹
        File f = new File("E:/工程");
        ExecutorService es = Executors.newFixedThreadPool(20);
        ifElse(f,es);
        es.shutdown();
    }
}
