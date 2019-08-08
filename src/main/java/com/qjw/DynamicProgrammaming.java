package com.qjw;

/**
 * 动态规划
 *
 * @author : qjw
 * @data : 2019/8/6
 */
public class DynamicProgrammaming {

    public static void main(String[] args) {
        int[] moneyOpt = {1, 3, 5};
        int needMoney = 9;
        int[][] exchange = exchange(moneyOpt, needMoney);
        for (int[] ints : exchange) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] exchange(int[] moneyOpt, int needMoney) {
        int[][] dpArr = new int[moneyOpt.length][needMoney];
        for (int i = 0; i < dpArr.length; i++) {
            for (int j = 0; j < dpArr[i].length; j++) {
                int shang = (j + 1) / moneyOpt[i];
                int yushu = (j + 1) % moneyOpt[i];
                int k = i;
                while (yushu > 0 && k > 0) {
                    k--;
                    shang += yushu / moneyOpt[k];
                    yushu = yushu % moneyOpt[k];
                }
                dpArr[i][j] = dpArr[i > 0 ? i - 1 : i][j] == 0 ? shang : Math.min(dpArr[i - 1][j], shang);
            }
        }
        return dpArr;
    }


}
