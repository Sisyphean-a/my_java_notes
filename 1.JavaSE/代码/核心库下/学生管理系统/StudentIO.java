package com.fusi.test20;

import java.io.*;

public class StudentIO {

    public static Object Input(){
        File f = new File("E:/student.txt");
        if (!f.exists()){
            try {
                f.createNewFile();
                Output(new StudentSystem());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ObjectInputStream ois = null;
        Object o = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("E:/student.txt"));
            o = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

    public static void Output(StudentSystem ss){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("E:/student.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            oos.writeObject(ss);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
