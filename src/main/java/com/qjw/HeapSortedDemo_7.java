package com.qjw;

import java.util.Arrays;

/**
 * @author : qjw
 * @data : 2019/7/23
 */
public class HeapSortedDemo_7 {

    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9};
//        int[] arr = {5, -2,-343,2, 4, 7, 3, 6,3644, 1, 8,34};

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        heapSortVideo(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 堆排序：升序(大根堆)
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int needJustMaxIndex = arr.length - 1;
        while (needJustMaxIndex > 0) {
            // 1.将数据调整位大根堆
            // 从最后一个非叶子节点开始调整
            for (int i = (needJustMaxIndex - 1) / 2; i >= 0; i--) {
                adjust(arr, i, needJustMaxIndex + 1);
            }

            // 2.交换
            int tmp = arr[0];
            arr[0] = arr[needJustMaxIndex];
            arr[needJustMaxIndex] = tmp;

            // 3.一下次调整
            needJustMaxIndex--;
        }
    }

    public static void heapSortVideo(int[] arr) {
        int tmp ;
        // 先调整一遍
        // 从最后一个非叶子节点开始调整
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeapVideo(arr, i, arr.length);
        }

        // 第一遍调整后交换，继续调整
        for (int j = arr.length - 1; j > 0; j--) {
            // 将第一个元素与大根堆的最后一个交换
            tmp = arr[j];
            arr[j] = arr[0];
            arr[0] = tmp;

            // 交换后再调整，减小调整的length
            adjustHeapVideo(arr, 0, j);
        }
    }

    /**
     * @param arr
     * @param noLeafIndex
     * @param length
     */
    private static void adjustHeapVideo(int[] arr, int noLeafIndex, int length) {
        int tmp = arr[noLeafIndex];
        // 递归调整左子树(节点)
        for (int k = noLeafIndex * 2 + 1; k < length; k = k * 2 + 1) {
            // 如果左子节点小于右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]){
                k ++ ; // k 指向右子节点
            }

            // 左(右)节点小于非叶子节点值
            if (arr[k] <= tmp){
                break;
            }
            // 非叶子节点大于左右节点，
            // 则把非叶子节点的值改为左右节点
            arr[noLeafIndex] = arr[k];
            // noLeafIndex指向最后一个左右节点
            noLeafIndex = k;
        }
        // 完成交换
        arr[noLeafIndex] = tmp;
    }

    /**
     * 将数组调整成大根堆
     *
     * @param arr             需要调整的数组
     * @param lastNoLeafIndex 最后一个非叶子节点的索引
     * @param needJustLength  需要调整的长度
     */
    private static void adjust(int[] arr, int lastNoLeafIndex, int needJustLength) {
        // 左子节点值
        int leftNodeValue = arr[lastNoLeafIndex * 2 + 1];

        // 右子节点值
        int rightNodeValue = 0;
        if (lastNoLeafIndex * 2 + 2 < needJustLength) {
            rightNodeValue = arr[lastNoLeafIndex * 2 + 2];
        }

        // 最后一个非叶子节点的值 小于 左右节点值，则交换
        while ((arr[lastNoLeafIndex] < leftNodeValue && lastNoLeafIndex * 2 + 1 < needJustLength)
                || (rightNodeValue != 0 && arr[lastNoLeafIndex] < rightNodeValue && lastNoLeafIndex * 2 + 2 < needJustLength)) {
            if (arr[lastNoLeafIndex] < leftNodeValue && lastNoLeafIndex * 2 + 1 < needJustLength) {
                int tmp = arr[lastNoLeafIndex];
                arr[lastNoLeafIndex] = leftNodeValue;
                arr[lastNoLeafIndex * 2 + 1] = tmp;
            }
            if (rightNodeValue != 0 && arr[lastNoLeafIndex] < rightNodeValue && lastNoLeafIndex * 2 + 2 < needJustLength) {
                int tmp = arr[lastNoLeafIndex];
                arr[lastNoLeafIndex] = rightNodeValue;
                arr[lastNoLeafIndex * 2 + 2] = tmp;
            }
        }
    }


}
