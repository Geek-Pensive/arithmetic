package com.qjw;

import java.util.Stack;

/**
 * @author : qjw
 * @data : 2019/7/15
 */
public class BinaryTreeDemo_4 {
    public static void main(String[] args) {
        int id = 0;
        BinaryTree tree = new BinaryTree(
                new TreeNode(5,
                        new TreeNode(2, id++),
                        new TreeNode(3,
                                new TreeNode(1, id++),
                                new TreeNode(4, id++), id++), id++)
        );
        tree.preOrder(tree.root);
        System.out.println("----------------");
        tree.inOrderNotRecursion();
/*        tree.inOrder(tree.root);
        System.out.println("----------------");
        tree.afterOrder(tree.root);
        System.out.println("----------------");*/


//        tree.deleteNode(2);
//        tree.preOrder(tree.root);
    }
}

class BinaryTree {

    TreeNode root;

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    void preOrder(TreeNode curr) {
        if (curr != null) {
            System.out.println(curr);
            preOrder(curr.left);
            preOrder(curr.right);
        }
    }

    void inOrder(TreeNode curr) {
        if (curr != null) {
            inOrder(curr.left);
            System.out.println(curr);
            inOrder(curr.right);
        }
    }

    void inOrderNotRecursion() {
        if (root == null) {
            return;
        }

        java.util.Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode tmp = root;//辅助节点
        stk.add(tmp);
        while (!stk.isEmpty()) {
            //只要你有左孩子，就将左孩子压入栈中
            if (tmp != null && tmp.left != null) {
                stk.add(tmp.left);
                tmp = tmp.left;
            } else {
                tmp = stk.pop();//弹出栈顶节点  左孩子--->根节点
                System.out.print(tmp.value + " ");//访问
                if (tmp != null && tmp.right != null) {//如果栈点元素有右孩子的话，将有节点压入栈中
                    stk.add(tmp.right);
                    tmp = tmp.right;
                } else
                    tmp = null;//p=stk.pop;已经访问过p了，p设置为null
            }
        }

    }

    void afterOrder(TreeNode curr) {
        if (curr != null) {
            afterOrder(curr.left);
            afterOrder(curr.right);
            System.out.println(curr);
        }
    }

    /**
     * 根据id删除节点或子树
     *
     * @param id
     */
    void deleteNode(int id) {
        if (this.root == null) {
            System.out.println("root is null,fail to delete node,id = " + id);
            return;
        }

        if (this.root.id == id) {
            this.root = null;
        } else {
            root.deleteNode(id);
        }
    }
}

class TreeNode {
    int id;
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value, TreeNode left, TreeNode right, int id) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.id = id;
    }

    /**
     * 根据id删除某个节点或者某个子树
     */
    void deleteNode(int id) {
        // 如果要删除的是左节点（子树），则删除
        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }

        // 如果要删除的是右节点（子树），则删除
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }

        // 向左子树、右子树递归删除
        if (this.left != null) {
            this.left.deleteNode(id);
        }
        if (this.right != null) {
            this.right.deleteNode(id);
        }
    }

    public TreeNode(int value, int id) {
        this.value = value;
        this.id = id;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
