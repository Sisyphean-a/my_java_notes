package com.fusi.test20;

import java.io.*;
import java.util.Scanner;

public class StudentSystemTest {

    public static void main(String[] args) {
        System.out.println("-------------------------------------");
        System.out.println("           欢迎使用学生管理系统         ");
        System.out.println("-------------------------------------");
        StudentSystem ss = null;
        StudentSystem si = (StudentSystem) StudentIO.Input();
        if (si != null) {
            ss = si;
        } else {
            ss = new StudentSystem();
        }
        while (true) {
            System.out.println("请输入你要进行的操作" +
                    "\n（1：增加  2：删除 3：查找 4：遍历 0：结束使用）：");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            if (num == 0){
                System.out.println("感谢您的使用，再见~");
                StudentIO.Output(ss);
                break;
            }
            switch (num) {
                case 1 -> {
                    System.out.println("请输入学生姓名和年龄（name age）:");
                    ss.addList(sc.next(), sc.nextInt());
                }
                case 2 -> {
                    while (true) {
                        System.out.println("请选择删除的方式（1：ID 2：姓名）：");
                        int idOrName = sc.nextInt();
                        if (idOrName == 1) {
                            System.out.println("----请输入要删除的ID号：");
                            ss.delList(sc.nextInt());
                            break;
                        } else if (idOrName == 2) {
                            System.out.println("----请输入要删除的姓名：");
                            ss.delList(sc.next());
                            break;
                        } else {
                            System.out.println("输入错误！请重新输入：");
                        }
                    }
                    ;
                }
                case 3 -> {
                    while (true) {
                        System.out.println("请选择查找的方式（1：ID 2：姓名）：");
                        int idOrName = sc.nextInt();
                        if (idOrName == 1) {
                            System.out.println("----请输入要查找的ID号：");
                            ss.search(sc.nextInt());
                            break;
                        } else if (idOrName == 2) {
                            System.out.println("----请输入要查找的姓名：");
                            ss.search(sc.next());
                            break;
                        } else {
                            System.out.println("输入错误！请重新输入：");
                        }
                    }
                    ;
                }
                case 4 -> ss.send();
                default -> System.out.println("输入错误，请重新输入：");
            }
        }
    }
}
