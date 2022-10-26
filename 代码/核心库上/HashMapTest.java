package com.fusi.test15;

import java.util.HashMap;
import java.util.Map;

/**
 * 准备一个 HashMap 集合，统计字符串"123,456,789,123,456"中每个数字字符串出现的次数并打印出来。
 */
public class HashMapTest {

    public static void main(String[] args) {
        String st = "123,456,789,123,456";
        String[] sArr = st.split(",");

        Map<String,Integer> map = new HashMap<>();
        System.out.println(map);

        for (String s : sArr) {
            if (!map.containsKey(s)) {
                map.put(s,1);
            } else {
                Integer integer = map.get(s);
                map.put(s,integer+1);
            }
        }

        System.out.println(map);
    }
}
