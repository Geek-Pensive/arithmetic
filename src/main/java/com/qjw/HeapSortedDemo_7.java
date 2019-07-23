package com.qjw;

/**
 * @author : qjw
 * @data : 2019/7/23
 */
public class HeapSortedDemo_7 {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
//        int[] arr = {5, 2, 4, 7, 3,6,1,8};
        heapSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
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
        if (lastNoLeafIndex * 2 + 2 < needJustLength){
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
