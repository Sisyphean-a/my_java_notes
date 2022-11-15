package com.fusi.test10;

import java.util.Random;

public class ArrayAdd {

    int[][] arr;

    public ArrayAdd(int one,int two){
        arr = new int[one][two];
        Random ra = new Random();
        for(int i=0;i<arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                arr[i][j] = ra.nextInt(100) + 1;
            }
        }
    }

    public void show(){
        for(int i=0;i<arr.length;i++){
            System.out.print("第" + (i+1) + "行： ");
            for (int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public void showX(){
        int ia;
        for(int i=0;i<arr.length;i++){
            ia = 0;
            for (int j=0;j<arr[i].length;j++){
                ia += arr[i][j];
            }
            System.out.println("第" + (i+1) + "行的值为" + ia);
        }
    }

    public void showY(){
        int ia;
        for(int i=0;i<arr.length;i++){
            ia = 0;
            for (int j=0;j<arr[i].length;j++){
                ia += arr[j][i];
            }
            System.out.println("第" + (i+1) + "列的值为" + ia);
        }
    }

    public void addLeft(){
        int ia = 0;
        for (int i=0 ; i< arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                if (i == j){
                    ia += arr[i][j];
                }
            }
        }
        System.out.println("从左上角到右下角的累加和为：" + ia);
    }

    public void addReght(){
        int ia = 0;
        for (int i=0 ; i< arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                if (i == (arr.length-j)){
                    ia += arr[i][j];
                }
            }
        }
        System.out.println("从右上角到左下角的累加和为：" + ia);
    }
}
