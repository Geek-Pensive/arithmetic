package com.qjw;

/**
 * @author : qjw
 * @data : 2019/7/17
 */
public class ThreadedBinaryTreeDemo_6 {

    public static void main(String[] args) {

        // tree
        InThreadedBinaryTree inThreadedBinaryTree = new InThreadedBinaryTree(
                // root
                new ThreadedTreeNode(1,
                // left
                new ThreadedTreeNode(3, new ThreadedTreeNode(8), new ThreadedTreeNode(10)),
                // right
                new ThreadedTreeNode(6, new ThreadedTreeNode(14))
            )
        );

        // 线索化
        inThreadedBinaryTree.inThrededNode(inThreadedBinaryTree.root);

        // 验证
        System.out.println("--------------8-----------------");
        // value=8的节点
        System.out.println(inThreadedBinaryTree.root.left.left.value);
        System.out.println(inThreadedBinaryTree.root.left.left.left);
        System.out.println(inThreadedBinaryTree.root.left.left.right.value);

        System.out.println("--------------10-----------------");
        // value=10的节点
        System.out.println(inThreadedBinaryTree.root.left.right.value);
        System.out.println(inThreadedBinaryTree.root.left.right.left.value);
        System.out.println(inThreadedBinaryTree.root.left.right.leftType);
        System.out.println(inThreadedBinaryTree.root.left.right.right.value);
        System.out.println(inThreadedBinaryTree.root.left.right.rightType);

        System.out.println("--------遍历-------------");
        inThreadedBinaryTree.inOrder();
    }

}

/**
 * 线索二叉树：
 * n个节点的完全二叉树中有n+1个空指针域
 * 利用这些空指针域，来存放改节点的前驱、后继节点，叫做线索化
 * 前驱、后继：在某种遍历顺序下(前、中、后)的遍历输出前后节点
 * 故有3中线索二叉树：前、中、后序线索二叉树
 */

/**
 * 中序线索二叉树
 */
class InThreadedBinaryTree{

    ThreadedTreeNode root;

    // 指向线索化当前节点的前驱节点
    ThreadedTreeNode pre;

    public InThreadedBinaryTree(ThreadedTreeNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "InThreadedBinaryTree{" +
                "root=" + root +
                ", pre=" + pre +
                '}';
    }

    /**
     * 中序遍历线索二叉树
     */
    void inOrder(){
        ThreadedTreeNode curr = root;

        // 当前不为空
        while (curr != null){

            // 找到当前节点最左边的那个leftType==1节点
            while (curr.leftType != 1) {
                curr = curr.left;
            }

            System.out.println(curr.value);

            // 输出后继节点链
            while (curr.rightType == 1){
                curr = curr.right;
                System.out.println(curr.value);
            }

            // 没有后继节点后，用最后一个后继节点的右子节点，替换curr节点
            curr = curr.right;
        }
    }

    /**
     * 中序线索化node
     * 先线索化左子树，再当前节点，再右子树
     * @param curr
     */
    void inThrededNode(ThreadedTreeNode curr){
        if (curr == null){
            return;
        }

        // 1.先线索化左子树
        inThrededNode(curr.left);

        // 2.再线索化当前节点
        if (curr.left == null){
            // 设置当前节点的前驱节点
             curr.left = pre;
            curr.leftType = 1;
        }
        // 处理后继节点
        if (pre != null && pre.right == null){
            // 让前驱节点的右指针执行当前节点
            pre.right = curr;
            pre.rightType = 1;
        }
        // !!! 每处理一个节点后，让当前节点指向下一个节点的前驱节点
        pre = curr;

        // 3.再右子树
        inThrededNode(curr.right);


    }
}
class ThreadedTreeNode{
    int value;
    ThreadedTreeNode left;
    ThreadedTreeNode right;

    // 左右节点(子树)类型,0代表子节点(子树)，1代表前驱后继
    int leftType;
    int rightType;


    public ThreadedTreeNode(int value, ThreadedTreeNode left, ThreadedTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public ThreadedTreeNode(int value) {
        this.value = value;
    }

    public ThreadedTreeNode(int value, ThreadedTreeNode left) {
        this.value = value;
        this.left = left;
    }

}



