package com.qjw;

/**
 * @author : qjw
 * @data : 2019/7/17
 */
public class ArrayBinaryTreeDemo_5{

    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder();
    }

}

/**
 * 用数组按层存储二叉树数据
 * 特点：
 * 1.必须是完全二叉树(最后一层从左到右依次都有元素)
 * 2.第n个元素的左节点是 2*n+1
 * 3.第n个元素的右节点是 2*n+2
 * 4.第n个元素的父节点是 (n-1)/2
 */
class ArrayBinaryTree{
    /**
     * 存储二叉树数据的数组
     */
    int [] arr ;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }
    public void preOrder(int currIndex){
        if (currIndex < arr.length && arr[currIndex] != 0){
            System.out.println(arr[currIndex]);
            preOrder(2 * currIndex + 1);
            preOrder(2 * currIndex + 2);
        }
    }

    public void inOrder(int currIndex){
        if (currIndex < arr.length && arr[currIndex] != 0){
            inOrder(2 * currIndex + 1);
            System.out.println(arr[currIndex]);
            inOrder(2 * currIndex + 2);
        }
    }

    public void afterOrder(int currIndex){
        if (currIndex < arr.length && arr[currIndex] != 0){
            afterOrder(2 * currIndex + 1);
            afterOrder(2 * currIndex + 2);
            System.out.println(arr[currIndex]);
        }
    }

}
