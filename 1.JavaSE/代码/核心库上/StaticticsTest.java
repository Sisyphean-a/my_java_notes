package com.fusi.test15;

/**
 * 编程统计字符串"ABCD123!@#$%ab"中大写字母、小写字母、数字、其它字符的个数并打印出来。
 * 48-57 数字  65-90 大写 97-122 小写
 */

public class StaticticsTest {

    public static void main(String[] args) {
        String st = "ABCD123!@#$%ab";
        System.out.println("目标字符串：" + st);
        byte[] bytes = st.getBytes();
        int[] arr = new int[4];
        for (byte b : bytes){
            if (b >= 48 && b <= 57){
                arr[0] += 1;
            } else if (b >= 65 && b <= 90){
                arr[1] += 1;
            } else if (b >= 97 && b <= 122) {
                arr[2] += 1;
            } else{
                arr[3] += 1;
            }
        }
        System.out.println("数字：" + arr[0] + " 大写字母：" + arr[1] +
                " 小写字母：" + arr[2] + " 其他：" + arr[3]);
    }
}
