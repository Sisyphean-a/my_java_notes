package com.fusi.test20;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * a.使用 List 集合实现简易的学生信息管理系统，要求打印字符界面提示用户选择相应的功能，
 *   根据用户输入的选择去实现增加、删除、修改、查找以及遍历所有学生信息的功能。
 * b.自定义学号异常类和年龄异常类，并在该成员变量不合理时产生异常对象并抛出。
 * c.当系统退出时将 List 集合中所有学生信息写入到文件中，当系统启动时读取文件中所有学生信息到 List 集合中。
 */

public class StudentSystem implements java.io.Serializable{
    @Serial
    private static final long serialVersionUID = 23947932434L;

    private int number = 1000;
    private final List<Student> studentList= new ArrayList<>();

    // 增加
    public void addList(String st , int age) {
//        studentList.size(),
        studentList.add(new Student(st,number,age));
        number++;
    }

    // 删除
    public void delList(int numId){
        studentList.remove(numId - 1000);
    }

    public void delList(String str){
        int i = lookList(str);
        if (i != -1){
            studentList.remove(i - 1000);
        } else {
            System.out.println("此用户不存在！");
        }
    }

    public int lookList(String name){
        for (Student str : studentList){
            if (str.getName().equals(name)){
                return str.getID();
            }
        }
        return -1;
    }

    // 查找
    public void search(String name){
        int i = lookList(name);
        System.out.println(studentList.get(i - 1000).toString());
    }

    public void search(int Id){
        System.out.println(studentList.get(Id - 1000).toString());
    }

    // 遍历
    public void send(){
        System.out.println("-------------------------------------");
        System.out.println("     ID          姓名          年龄   ");
        if (!studentList.isEmpty()) {
            for (Student str : studentList) {
                System.out.println(str.send());
            }
        } else {
            System.out.println("                 null");
        }
        System.out.println("-------------------------------------");
    }
}
