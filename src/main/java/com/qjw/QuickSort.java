package com.qjw;

import java.util.Arrays;

/**
 * @author : qjw
 * @data : 2019/7/1
 */
public class QuickSort {

    public static void main(String[] args) {
        Integer[] arr = {5, 2, 4, 7, 3, 6};
//        Integer[] arr = {5, 2, -4, 99, 3, 1, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.asList(arr));
    }

    static void quickSort(Integer[] arr, int left, int right) {
        int midIndex = (left + right) / 2;
        int base = arr[midIndex];

        int ltmp = left, rtmp = right;
        // 只要ltmp < rtmp，就一直找，一直换
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

            if (arr[ltmp] == base) {
                rtmp--;
            }
            if (arr[rtmp] == base) {
                ltmp++;
            }
        }

        if (ltmp == rtmp) {
            ltmp++;
            rtmp--;
        }

        if (rtmp > left) {
            quickSort(arr, left, rtmp);
        }
        if (ltmp < right) {
            quickSort(arr, ltmp, right);
        }

    }
}
