package com.fusi.test15;

import java.util.*;

/**
 *  （1）首先准备 54 张扑克牌并打乱顺序。
 *  （2）由三个玩家交替摸牌，每人 17 张扑克牌，最后三张留作底牌。
 *  （3）查看三个玩家手中的扑克牌和底牌。
 *  （4）其中玩家手中的扑克牌需要按照大小顺序打印，规则如下：
 *   手中扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3
 */

public class BucketMainTest {

    static void issue(List<Integer> co,Map<Integer,String> poker){
        List<String> stCol = Arrays.asList("♠", "♥", "♣", "♦");
        List<String> stNum = Arrays.asList("2","A","K","Q","J","10","9","8","7","6","5","4","3");
        // 两个列表，一个是牌码，一个是字典，用于查询牌码对应的牌
        Integer in = 0;
        co.add(in);
        poker.put(in++,"小王");
        co.add(in);
        poker.put(in++,"大王");
        for (String i : stNum){
            for (String s : stCol){
                co.add(in);
                poker.put(in++,s+i);
            }
        }
    }

    static void show(String name , List<Integer> coX , Map<Integer,String> poker){
        System.out.print(name + "的牌为：");
        for (Integer i : coX){
            System.out.print(poker.get(i) + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        List<Integer> co = new ArrayList<>();
        Map<Integer,String> poker = new HashMap<>();

        List<Integer> coA = new ArrayList<>();
        List<Integer> coB = new ArrayList<>();
        List<Integer> coC = new ArrayList<>();
        List<Integer> coD = new ArrayList<>();

        BucketMainTest.issue(co,poker);
        // 打乱顺序
        Collections.shuffle(co);


        // 分牌
        for (int a = 0; a < co.size() -3 ; a += 3){
            coA.add(co.get(a));
            coB.add(co.get(a+1));
            coC.add(co.get(a+2));

        }
        for (int i = 51 ; i<co.size() ; i++){
            coD.add(co.get(i));
        }

        Collections.sort(coA);
        Collections.sort(coB);
        Collections.sort(coC);
        Collections.sort(coD);

        show("卡牌",co,poker);
        show("1号",coA,poker);
        show("2号",coB,poker);
        show("3号",coC,poker);
        show("coD",coD,poker);
    }
}
