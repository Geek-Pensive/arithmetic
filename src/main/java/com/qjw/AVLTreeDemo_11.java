package com.qjw;

/**
 * AVL树和红黑树的区别：AVL是严格平衡的，用于读多写少的情况，因为增加和删除旋转次数多
 *
 * @author : qjw
 * @data : 2019/7/31
 */
public class AVLTreeDemo_11 {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        for (int i : arr) {
            avlTree.addNode(new AVLTreeNode(i));
        }

        avlTree.inOrder(avlTree.root);
        System.out.println("树的高度：" + avlTree.root.height());
        System.out.println("左子树的高度：" + avlTree.root.leftHeight());
        System.out.println("右子树的高度：" + avlTree.root.rightHeight());
        System.out.println("根节点：" + avlTree.root);
    }
}

class AVLTree {
    AVLTreeNode root;

    /**
     * 添加节点
     *
     * @param newNode
     */
    public void addNode(AVLTreeNode newNode) {
        if (newNode == null) {
            System.out.println("node must not be null");
            return;
        }

        if (root == null) {
            root = newNode;
            return;
        }

        // 递归添加
        addNode(root, newNode);
    }

    private void addNode(AVLTreeNode curr, AVLTreeNode newNode) {
        if (newNode.value < curr.value) {
            if (curr.left == null) {
                curr.left = newNode;
            } else {
                addNode(curr.left, newNode);
            }
        } else {
            if (curr.right == null) {
                curr.right = newNode;
            } else {
                addNode(curr.right, newNode);
            }
        }

        // 如果(右子树高度-左子树高度) > 1 则左旋
        if (curr.rightHeight() - curr.leftHeight() > 1) {
            if (curr.right.leftHeight() > curr.leftHeight()) {
                curr.right.rightRotate();
            }
            curr.leftRotate();
            // 如果(左子树高度-右子树高度) > 1 则右旋
        } else if (curr.leftHeight() - curr.rightHeight() > 1) {
            // 如果当前节点的左子树的右子树 的高度 大于 当前节点的右子树，则先需要对左子树进行左旋 如:{10, 11, 7, 6, 8, 9}
            if (curr.left.rightHeight() > curr.rightHeight()) {
                curr.left.leftRotate();
            }
            curr.rightRotate();
        }
    }

    public void inOrder(AVLTreeNode curr) {
        if (curr != null) {
            inOrder(curr.left);
            System.out.println(curr.value);
            inOrder(curr.right);
        }
    }

    public AVLTree(AVLTreeNode root) {
        this.root = root;
    }

    public AVLTree() {
    }

    @Override
    public String toString() {
        return "AVLTree{" +
                "root=" + root +
                '}';
    }
}

class AVLTreeNode {
    int value;
    AVLTreeNode left;
    AVLTreeNode right;

    public AVLTreeNode(int value) {
        this.value = value;
    }

    /**
     * 以当前节点为根节点，进行右旋
     * 1.创建一个新节点，值等于当前根节点的值
     * 2.把新节点的右子树设置为当前节点的右子树
     * 3.把新节点的左子树设置为当前节点的左子树的右子树
     * 4.把当前节点的值换为左子节点的值
     * 5.把当前节点的左子树设置为左子树的左子树
     * 6.把当前节点的右子树设置为新节点
     */
    public void rightRotate() {
        // 1.
        AVLTreeNode newNode = new AVLTreeNode(this.value);

        // 2.
        newNode.right = this.right;

        // 3.
        newNode.left = this.left.right;

        // 4.
        this.value = this.left.value;

        // 5.
        this.left = this.left.left;

        // 6.
        this.right = newNode;
    }

    /**
     * 以当前节点为根节点，进行左旋
     * 1.创建一个新节点，值等于当前根节点的值
     * 2.把新节点的左子树设置为当前节点的左子树
     * 3.把新节点的右子树设置为当前节点的右子树的左子树
     * 4.把当前节点的值换为右子节点的值
     * 5.把当前节点的右子树设置为右子树的右子树
     * 6.把当前节点的左子树设置为新节点
     */
    public void leftRotate() {
        // 1.
        AVLTreeNode newNode = new AVLTreeNode(this.value);

        // 2.
        newNode.left = this.left;

        // 3.
        newNode.right = this.right.left;

        // 4.
        this.value = this.right.value;

        // 5.
        this.right = this.right.right;

        // 6.
        this.left = newNode;
    }

    /**
     * 获取当前节点的右子树高度
     *
     * @return
     */
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }


    /**
     * 获取当前节点的左子树高度
     *
     * @return
     */
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    /**
     * 获取以当前节点为根节点的树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public AVLTreeNode search(int value) {
        if (this.value == value) {
            return this;
        } else if (value > this.value) {
            if (null == this.right) {
                return null;
            }
            // 向右递归
            return this.right.search(value);
        } else {
            if (null == this.left) {
                return null;
            }
            return this.left.search(value);
        }
    }


    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

