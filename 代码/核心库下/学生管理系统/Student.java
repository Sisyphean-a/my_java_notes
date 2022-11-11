package com.fusi.test20;

/**
 * a.使用 List 集合实现简易的学生信息管理系统，要求打印字符界面提示用户选择相应的功 能，
 *   根据用户输入的选择去实现增加、删除、修改、查找以及遍历所有学生信息的功能。
 * b.自定义学号异常类和年龄异常类，并在该成员变量不合理时产生异常对象并抛出。
 * c.当系统退出时将 List 集合中所有学生信息写入到文件中，当系统启动时读取文件中所 有学生信息到 List 集合中。
 */
public class Student implements java.io.Serializable{
    private int ID;
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int ID, int age) {
        setAge(age);
        setID(ID);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        if (ID > 0) {
            this.ID = ID;
        } else {
            try {
                throw new AgeIdException("ID不合理！");
            } catch (AgeIdException e) {
                e.printStackTrace();
            }
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age= age;
        } else {
            try {
                throw new AgeIdException("年龄不合理！");
            } catch (AgeIdException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String send(){
        return "     " + ID + "        " + name + "         " + age;
    }
}

class AgeIdException extends Exception{
    public AgeIdException() {
    }

    public AgeIdException(String message) {
        // 通过传入的参数来使用父类的输出
        super(message);
    }
}
