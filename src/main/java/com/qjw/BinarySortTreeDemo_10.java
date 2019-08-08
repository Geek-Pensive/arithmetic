package com.qjw;

/**
 * @author : qjw
 * @data : 2019/7/26
 */
public class BinarySortTreeDemo_10 {
    public static void main(String[] args) {

        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {7, 3, 1, 5, 9, 12, 10, 0};
        for (int i : arr) {
            binarySortTree.addNode(new BinarySortTreeNode(i));
        }
        binarySortTree.inOrder(binarySortTree.root);
        System.out.println("--------------------");

        binarySortTree.delete(7);
        binarySortTree.delete(3);
        binarySortTree.delete(1);
        binarySortTree.delete(5);
        binarySortTree.delete(9);
        binarySortTree.delete(12);
        binarySortTree.delete(10);
        binarySortTree.delete(0);
        binarySortTree.inOrder(binarySortTree.root);
        System.out.println(binarySortTree);
    }
}

/**
 * 二叉排序树(BTS):每个节点的左子节点的值 小于 右子节点的值
 * 中序遍历后升序排列
 */
class BinarySortTree {
    BinarySortTreeNode root;

    public BinarySortTree() {
    }

    @Override
    public String toString() {
        return "BinarySortTree{" +
                "root=" + root +
                '}';
    }

    /**
     * 删除BTS的节点
     * 1.删除叶子节点：确定该节点是父节点左或者右
     * 2.删除只有一颗子树的节点:判断该节点有左或者右节点，判断该节点是父节点的左还是右。（2*2=4）
     * 3.删除有两颗子树的节点：从该节点的右子树中找一个最小的节点，值用tmp保存，删除该最小的节点，最后把target=tmp
     *
     * @param value
     */
    public void delete(int value) {
        if (this.root == null) {
            return;
        }

        BinarySortTreeNode target = search(value);
        if (target == null) {
            System.out.println("没找到要删除的节点");
            return;
        }

        if (this.root.left == null && this.root.right == null) {
            // 删除root节点
            this.root = null;
            return;
        }

        BinarySortTreeNode targetParent = searchParent(value);

        // 1. target是叶子节点
        if (target.left == null && target.right == null) {
            // target是parent的左子节点
            if (targetParent.left != null && targetParent.left.value == value) {
                targetParent.left = null;
            } else if (targetParent.right != null && targetParent.right.value == value) {
                targetParent.right = null;
            }
        }
        // 3.删除有两颗子树的节点
        else if (target.left != null && target.right != null) {
            target.value = delRightTreeMin(target.right);
        }
        // 2.删除只有一颗子树的节点
        else {
            if (target.left != null) {
                // 注意这里
                if (targetParent != null) {
                    if (targetParent.left.value == value) {
                        targetParent.left = target.left;
                    } else if (targetParent.right.value == value) {
                        targetParent.right = target.left;
                    }
                } else {
                    this.root = target.left;
                }
            } else {
                if (targetParent != null) {
                    if (targetParent.left.value == value) {
                        targetParent.left = target.right;
                    } else if (targetParent.right.value == value) {
                        targetParent.right = target.right;
                    }
                } else {
                    this.root = target.right;
                }

            }
        }

    }

    /**
     * 从该节点的右子树中找一个最小的节点，值用tmp保存，删除该最小的节点，最后把target=tmp
     *
     * @param rightTreeRoot
     * @return
     */
    private int delRightTreeMin(BinarySortTreeNode rightTreeRoot) {
        BinarySortTreeNode target = rightTreeRoot;

        for (; target.left != null; target = target.left) ;

        int tmp = target.value;

        delete(tmp);
        return tmp;
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public BinarySortTreeNode search(int value) {
        if (this.root == null) {
            return null;
        }
        return this.root.search(value);
    }

    /**
     * 查找父节点
     *
     * @param value
     * @return
     */
    public BinarySortTreeNode searchParent(int value) {
        if (this.root == null) {
            return null;
        }

        return this.root.searchParent(value);
    }


    /**
     * 添加节点
     *
     * @param newNode
     */
    public void addNode(BinarySortTreeNode newNode) {
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

    private void addNode(BinarySortTreeNode curr, BinarySortTreeNode newNode) {
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
    }


    public void inOrder(BinarySortTreeNode curr) {
        if (curr != null) {
            inOrder(curr.left);
            System.out.println(curr.value);
            inOrder(curr.right);
        }
    }
}

class BinarySortTreeNode {
    int value;
    BinarySortTreeNode left;
    BinarySortTreeNode right;

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public BinarySortTreeNode search(int value) {
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

    /**
     * 查找节点的父节点
     *
     * @param value
     * @return
     */
    public BinarySortTreeNode searchParent(int value) {
        // 是当期节点的左右节点，返回当前节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // value小于当前值，向左递归
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            }
            if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
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

