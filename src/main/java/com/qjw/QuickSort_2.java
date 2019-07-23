package com.qjw;

import java.util.Arrays;

/**
 * @author : qjw
 * @data : 2019/7/1
 */
public class QuickSort_2 {

    public static void main(String[] args) {
        Integer[] arr = {4, 2, 4, 7, 3, 6};
//        Integer[] arr = {5, 2, -4, 99, 3, 1, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.asList(arr));
    }


    static void quickSort(Integer[] arr, int left, int right) {
        int midIndex = (left + right) / 2;
        int base = arr[left];

        int ltmp = left, rtmp = right;
        // 只要ltmp < rtmp，就一直找，一直换,保证base的左边小于base，右边大于base
        while (ltmp < rtmp) {
            // 1.从左边找比base大的数
            while (arr[ltmp] < base)
                ltmp++;
            // 2.找比base小的数
            while (arr[rtmp] > base)
                rtmp--;

            if (ltmp >= rtmp) {
                // 找不到了
                break;
            }

            // 3.交换
            int tmp = arr[ltmp];
            arr[ltmp] = arr[rtmp];
            arr[rtmp] = tmp;

            // 若交换前的值与base相同，则指针移动
            if (arr[rtmp] == base) {
                ltmp++;
            }
            if (arr[ltmp] == base) {
                rtmp--;
            }

        }

        // 如果左右临时指针相同，则各走一步
        if (ltmp == rtmp) {
            ltmp++;
            rtmp--;
        }

        // 向左递归,分治计算
        if (rtmp > left) {
            quickSort(arr, left, rtmp);
        }
        // 向右递归,分治计算
        if (ltmp < right) {
            quickSort(arr, ltmp, right);
        }

    }
}
