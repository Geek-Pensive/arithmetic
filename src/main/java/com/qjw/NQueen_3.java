package com.qjw;

/**
 * @author : qjw
 * @data : 2019/7/12
 */
public class NQueen_3 {

    public static void main(String[] args) {
        NQueenDome queenDome = new NQueenDome(9);
        queenDome.check(0);
        System.out.println(queenDome.way);
    }

}

class NQueenDome {

    private int queenNum;

    /**
     * 数组第i个的值为v表示：第i+1个皇后在v+1列
     */
    private Integer[] queenPosition;

    public NQueenDome(int queenNum) {
        this.queenNum = queenNum;
        this.queenPosition = new Integer[queenNum];
    }

    // N皇后摆放的方式
    int way = 0;


    /**
     * 放入第n个皇后，进行试探并回溯
     * @param n
     */
    public void check(int n) {
        // 如果是最后一个皇后，则返回
        if (n == queenNum) {
            print();
            way++;
            return;
        }

        // 遍历所有列
        for (int i = 0; i < queenNum; i++) {
            // 尝试放入第n个皇后皇后
            queenPosition[n] = i;
            // 判断当前第n个皇后，是否有冲突
            if (judgeConflict(n)) {
                // 若无冲突，则放入第n+1个皇后
                check(n + 1);
            }
            // 若当前第n个皇后，有冲突，则自动进行尝试放置下一列
        }
    }

    /**
     * 判断冲突：是否在同行、同列、同斜线
     *
     * @param n 第n个皇后
     * @return 是否成功（没冲突）
     */
    private boolean judgeConflict(int n) {
        // 同行
        if (null == queenPosition[n]) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            // 同列
            if (queenPosition[i] == queenPosition[n]
                    // 同斜线
                    || Math.abs(n - i) == Math.abs(queenPosition[n] - queenPosition[i])) {
                return false;
            }
        }

        return true;
    }

    private void print() {
        for (Integer integer : queenPosition) {
            System.out.print(integer + "\t");
        }
        System.out.println();
    }
}
