package com.qjw;

/**
 * 分治算法
 * @author : qjw
 * @data : 2019/8/6
 */
public class HanoiTower_12 {

    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    /**
     * 把A塔上的所有盘移动到塔C
     *
     * @param num 圆盘的个数
     * @param a   原存放盘的塔
     * @param b   借助的临时塔
     * @param c   目的塔
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (1 == num) {
            System.out.println("第" + "1" + "个盘从" + a + "——>" + c);
        } else {
            // 若盘数>=2,
            // 1. 则把上面的num-1个盘移动到临时塔b,(借助c塔)
            hanoiTower(num - 1, a, c, b);
            // 2. 把塔a最下面一个盘移到塔c
            System.out.println("第" + num + "个盘从" + a + "——>" + c);
            // 3. 把塔b的num-1个盘移动到塔c
            hanoiTower(num - 1, b, a, c);
        }
    }
}
