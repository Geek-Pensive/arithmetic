package com.qjw;

import scala.Int;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 赫夫曼编码
 *
 * @author : qjw
 * @data : 2019/7/25
 */
public class HuffmanCode_9 {

    /**
     * 存放原字符和编译后的二进制位
     */
    private static Map<Byte, String> huffmanCodeMap;

    public static void main(String[] args) {
        String string = "i like like like java do you like a java";
        System.out.println("原字节数组:"+Arrays.toString(string.getBytes()));

        byte[] res = huffmanEncode(string);

        System.out.println("编码后的字节数组:"+Arrays.toString(res));

        // TODO 解码
    }



    /**
     * 赫夫曼编码
     *
     * @param string
     * @return
     */
    private static byte[] huffmanEncode(String string) {
        byte[] bytes = string.getBytes();
        // 1.统计频次
        List<HuffmanCodeNode> nodes = getNodeList(bytes);
        // 2.根据出现的次数创建赫夫曼树
        HuffmanCodeNode root = createHuffmanTree(nodes);
        // 3.对树的叶子节点进行编码
        Map<Byte, String> huffmanCodeMap = getHuffmanCodeMap(root);
        // 4.对字符串进行编码,返回二进制位位符串
        String bin = encode(bytes);
        // 5.对二进制行进编码:8为位一个字节
        return bin2Byte(bin);
    }

    /**
     * 从后向前编码
     * @param bin
     * @return byte: -128 ~ 127
     */
    private static byte[] bin2Byte(String bin) {
        byte[] res = new byte[(bin.length() + 7) / 8];
        List<Byte> resList = new ArrayList<Byte>((bin.length() + 7) / 8);
        char[] chars = bin.toCharArray();
        for (int i = chars.length - 1; i >= 0; i -= 8) {
            StringBuilder tmp = new StringBuilder();
            for (int j = i, t = j - 8; j > t && j >= 0; j--) {
                tmp.insert(0, chars[j]);
            }

            short i2 = Short.parseShort(tmp.toString(), 2);
            byte b = (byte) (i2 & 0xFF);      // short : 2个字节，取低位
            resList.add(b);
        }
        for (int i = 0; i < resList.size(); i++) {
            res[resList.size() - 1 - i] = resList.get(i);
        }
        return res;
    }

    private static String encode(byte[] bytes) {
        StringBuilder res = new StringBuilder("");
        for (byte aByte : bytes) {
            res.append(huffmanCodeMap.get(aByte));
        }
        return res.toString();
    }

    private static Map<Byte, String> getHuffmanCodeMap(HuffmanCodeNode root) {
        huffmanCodeMap = new ConcurrentHashMap<Byte, String>();

        StringBuilder init = new StringBuilder("");
        getHuffmanCodeMap(root, "", init);

        return huffmanCodeMap;
    }

    /**
     * 递归对赫夫曼树的叶子节点进行编码，并放入map
     *
     * @param curr    递归的当前节点
     * @param oneChar 路径的编码，左=0，右=1
     * @param code    路径已有编码
     */
    private static void getHuffmanCodeMap(HuffmanCodeNode curr, String oneChar, StringBuilder code) {
        // 这里必须用临时变量存储，因为要回溯
        StringBuilder tmp = new StringBuilder(code);
        tmp.append(oneChar);

        if (curr != null) {
            // 如果是非叶子节点
            if (curr.data == null) {
                // 以下两句递归因为有回溯，所有必须用tmp
                getHuffmanCodeMap(curr.left, "0", tmp);
                getHuffmanCodeMap(curr.right, "1", tmp);
            } else {    // 如果是非叶子节点
                huffmanCodeMap.put(curr.data, tmp.toString());
            }
        }

    }


    private static HuffmanCodeNode createHuffmanTree(List<HuffmanCodeNode> list) {

        while (list.size() > 1) {
            Collections.sort(list);

            HuffmanCodeNode leftNode = list.get(0);
            HuffmanCodeNode rightNode = list.get(1);
            HuffmanCodeNode rootTmp = new HuffmanCodeNode(null, leftNode.weight + rightNode.weight, leftNode, rightNode);

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(rootTmp);
        }

        return list.get(0);
    }

    private static List<HuffmanCodeNode> getNodeList(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<Byte, Integer>();
        for (byte aByte : bytes) {
            if (map.containsKey(aByte)) {
                map.put(aByte, map.get(aByte) + 1);
            } else {
                map.put(aByte, 1);
            }
        }

        List<HuffmanCodeNode> res = new ArrayList<HuffmanCodeNode>(map.size());
        for (Map.Entry<Byte, Integer> byteIntegerEntry : map.entrySet()) {
            res.add(new HuffmanCodeNode(byteIntegerEntry.getKey(), byteIntegerEntry.getValue()));
        }
        return res;
    }


}

class HuffmanCodeNode implements Comparable<HuffmanCodeNode> {
    Byte data;              // 存放数据，如'a':97,' ':32
    int weight;             // 权值：表示字符出现的次数

    HuffmanCodeNode left;
    HuffmanCodeNode right;

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public HuffmanCodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public HuffmanCodeNode(Byte data, int weight, HuffmanCodeNode left, HuffmanCodeNode right) {
        this.data = data;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public int compareTo(HuffmanCodeNode other) {
        return this.weight - other.weight;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
