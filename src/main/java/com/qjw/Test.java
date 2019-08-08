package com.qjw;

/**
 * @author : qjw
 * @data : 2019/8/8
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(test());

    }

    public static String test(){
        String a = null;
        if (a == null){
            throw new NullPointerException("a is null");
        }
        System.out.println("-------------");
        return a;
    }
}
