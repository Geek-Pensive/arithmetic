package com.qjw;

/**
 * @author : qjw
 * @data : 2019/6/20
 */
public class SingleLinkedListDemo_1 {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add2Tail("qjw1");
        linkedList.add2Tail("qjw2");
        linkedList.add2Tail("qjw3");
        linkedList.add2Tail("qjw4");
        linkedList.add2Tail("qjw5");
//        linkedList.delete("qjw3");
        linkedList.print();
        linkedList.reverseNoRecursion();
        System.out.println();
        linkedList.print();
        linkedList.reverseNoRecursion2();
        System.out.println();
        linkedList.print();

        linkedList.reverseRecursion();
        System.out.println();
        linkedList.print();

    }
}

class SingleLinkedList {
    ListNode head;

    public SingleLinkedList() {
        ListNode node = new ListNode("HEAD", null);
        this.head = node;
    }

    public void reverseNoRecursion() {
        ListNode cur = this.head.next;
        for (; cur != null; cur = cur.next) {
            this.delete(cur.name);
            this.add2Head(cur.name);
        }
    }

    public void reverseNoRecursion2() {
        ListNode cur = this.head.next;
        ListNode next;
        ListNode newHead = new ListNode("NEW_HEAD", null);
        while (cur != null) {
            next = cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = next;
        }
        this.head = newHead;
    }

    public void reverseRecursion() {
        ListNode newHead = new ListNode("NEW_HEAD2", null);
        ListNode newHeadRes = recursion(this.head.next, newHead);
        this.head = newHeadRes;

    }

    public ListNode recursion(ListNode cur,ListNode newHead) {
        if (cur == null){
            return newHead;
        }
        ListNode next;
        next = cur.next;
        cur.next = newHead.next;
        newHead.next = cur;

        recursion(next,newHead);
        return newHead;
    }

    public void print() {
        ListNode cur = this.head;
        for (; cur.next != null; cur = cur.next) {
            System.out.print(cur.name + "-->");
        }
        System.out.print(cur.name);
    }

    public void add2Tail(String name) {
        ListNode node = new ListNode(name, null);
        ListNode tail = this.head;
        for (; tail.next != null; tail = tail.next) ;
        tail.next = node;
    }

    public void add2Head(String name) {
        ListNode node = new ListNode(name, null);
        node.next = this.head.next;
        this.head.next = node;
    }

    public void delete(String name) {
        ListNode del = this.head.next;
        ListNode cdelPre = this.head;
        for (; del.name != name; cdelPre = cdelPre.next, del = cdelPre.next) ;
        cdelPre.next = del.next;
    }
}

class ListNode {
    public String name;
    public ListNode next;

    public ListNode(String name, ListNode next) {
        this.name = name;
        this.next = next;
    }
}
