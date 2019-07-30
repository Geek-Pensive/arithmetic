package com.qjw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 哈夫曼树：
 *  定n个权值作为n个叶子结点，构造一棵二叉树，若该树的带权路径长度达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman Tree)。
 *      哈夫曼树是带权路径长度(WPL)最短的树，权值较大的结点离根较近。
 *   1、路径和路径长度：在一棵树中，从一个结点往下可以达到的孩子或孙子结点之间的通路，称为路径。通路中分支的数目称为路径长度。若规定根结点的层数为1，则从根结点到第L层结点的路径长度为L-1
 *   2、结点的权及带权路径长度：若将树中结点赋给一个有着某种含义的数值，则这个数值称为该结点的权。结点的带权路径长度为：从根结点到该结点之间的路径长度与该结点的权的乘积。
 *   3、树的带权路径长度：树的带权路径长度规定为所有叶子结点的带权路径长度之和，记为WPL。
 * @author : qjw
 * @data : 2019/7/24
 */
public class HuffmanTree_8 {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTreeNode huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrder(huffmanTree);
    }

    public static HuffmanTreeNode createHuffmanTree(int[] arr) {
        List<HuffmanTreeNode> list = new ArrayList<HuffmanTreeNode>();
        for (int i : arr) {
            list.add(new HuffmanTreeNode(i));
        }

        // 只要list元素的个数大于1个
        while (list.size() > 1) {
            // 1.排序
            Collections.sort(list);

            // 2.取最小的两个元素形成子树
            HuffmanTreeNode leftNode = list.get(0);
            HuffmanTreeNode rightNode = list.get(1);
            HuffmanTreeNode rootTmp = new HuffmanTreeNode(leftNode.value + rightNode.value, leftNode, rightNode);

            // 3.删除左右节点，加入子树的根节点
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(rootTmp);
        }

        return list.get(0);
    }
}

class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
    int value;
    HuffmanTreeNode left;
    HuffmanTreeNode right;

    public void preOrder(HuffmanTreeNode curr) {
        if (curr != null) {
            System.out.println(curr.value);
            preOrder(curr.left);
            preOrder(curr.right);
        }
    }

    public HuffmanTreeNode(int value, HuffmanTreeNode left, HuffmanTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public HuffmanTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanTreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    // 升序(返回负数代表小于)
    public int compareTo(HuffmanTreeNode other) {
        return this.value - other.value;
    }
}